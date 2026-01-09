package worldmap;

import entity.Coordinate;
import entity.Eatable;
import entity.Entity;

import java.util.*;
import java.util.function.Predicate;

public class Pathfinder {
    private final WorldMap worldMap;

    public Pathfinder(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public List<Coordinate> findFood(Coordinate start, Predicate<Entity> foodType) {
        TreeNode root = new TreeNode(start);
        return findFood(root, foodType);
    }

    public List<Coordinate> findFood(Coordinate start, Class<? extends Eatable> foodType) {
        TreeNode root = new TreeNode(start);
        return findFood(root, foodType);
    }

    private List<Coordinate> findFood(TreeNode root, Predicate<Entity> foodType) {
        Queue<TreeNode> toBeVisited = new ArrayDeque<>();
        List<Coordinate> result = new ArrayList<>();
        Set<TreeNode> visitedCells = new HashSet<>();
        toBeVisited.add(root);
        while (!toBeVisited.isEmpty()) {
            TreeNode current = toBeVisited.poll();
            visitedCells.add(current);
            Optional<Entity> entity = worldMap.getEntityFromCell(current.coordinate);
            if (isFoodReached(entity.orElse(null), foodType)) {
                result = restorePath(current);
                break;
            }
            List<Coordinate> adjacent = worldMap.getAdjacentCoordinates(current.coordinate);
            for (Coordinate coordinate : adjacent) {
                TreeNode neighbour = new TreeNode(current, coordinate);
                if (
                        !toBeVisited.contains(neighbour) &&
                                !visitedCells.contains(neighbour) &&
                                (worldMap.isEmptyCell(coordinate) || isFoodReached(worldMap.getEntityFromCell(coordinate).orElse(null), foodType))
                ) {
                    toBeVisited.add(neighbour);
                }
            }

        }
        return result;
    }

    private List<Coordinate> findFood(TreeNode root, Class<? extends Eatable> foodType) {
        Queue<TreeNode> toBeVisited = new ArrayDeque<>();
        List<Coordinate> result = new ArrayList<>();
        Set<TreeNode> visitedCells = new HashSet<>();
        toBeVisited.add(root);
        while (!toBeVisited.isEmpty()) {
            TreeNode current = toBeVisited.poll();
            visitedCells.add(current);
            Optional<Entity> entity = worldMap.getEntityFromCell(current.coordinate);
            if (isFoodReached(entity.orElse(null), foodType)) {
                result = restorePath(current);
                break;
            }
            List<Coordinate> adjacent = worldMap.getAdjacentCoordinates(current.coordinate);
            for (Coordinate coordinate : adjacent) {
                TreeNode neighbour = new TreeNode(current, coordinate);
                if (
                        !toBeVisited.contains(neighbour) &&
                                !visitedCells.contains(neighbour) &&
                                (worldMap.isEmptyCell(coordinate) || isFoodReached(worldMap.getEntityFromCell(coordinate).orElse(null), foodType))
                ) {
                    toBeVisited.add(neighbour);
                }
            }

        }
        return result;
    }

    private boolean isFoodReached(Entity entity, Predicate<Entity> cellType) {
        return cellType.test(entity);
    }

    private boolean isFoodReached(Entity entity, Class<? extends Eatable> cellType) {
        return cellType.isInstance(entity);
    }

    private List<Coordinate> restorePath(TreeNode target) {
        List<Coordinate> result = new ArrayList<>();
        while (target.parent != null) {
            result.add(target.coordinate);
            target = target.parent;
        }
        Collections.reverse(result);
        return result;
    }

    private static class TreeNode {
        TreeNode parent;
        Coordinate coordinate;

        public TreeNode(TreeNode parent, Coordinate coordinate) {
            this(coordinate);
            this.parent = parent;
        }

        public TreeNode(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode treeNode = (TreeNode) o;
            return Objects.equals(coordinate, treeNode.coordinate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(coordinate);
        }
    }
}
