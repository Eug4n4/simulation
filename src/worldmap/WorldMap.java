package worldmap;

import entity.Coordinate;
import entity.Entity;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldMap {
    private final int width;
    private final int height;
    private final Map<Coordinate, Entity> occupiedCells = new HashMap<>();

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Optional<Entity> getEntityFromCell(Coordinate coordinate) {

        if (!isEmptyCell(coordinate)) {
            return Optional.of(occupiedCells.get(coordinate));
        }
        return Optional.empty();
    }

    public Coordinate getEntityCoordinate(Entity entity) {
        return occupiedCells.keySet()
                .stream()
                .filter(coordinate -> occupiedCells.get(coordinate) == entity)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public Coordinate getRandomEmptyCoordinate() {
        Random random = new Random();
        Coordinate coordinate;
        do {
            coordinate = new Coordinate(random.nextInt(getHeight()), random.nextInt(getWidth()));
        } while (!isEmptyCell(coordinate));
        return coordinate;
    }

    public List<Coordinate> getAdjacentCoordinates(Coordinate coordinate) {
        int row = coordinate.row();
        int column = coordinate.column();
        return Stream.of(new Coordinate(row - 1, column - 1),
                        new Coordinate(row - 1, column),
                        new Coordinate(row - 1, column + 1),
                        new Coordinate(row, column + 1),
                        new Coordinate(row + 1, column + 1),
                        new Coordinate(row + 1, column),
                        new Coordinate(row + 1, column - 1),
                        new Coordinate(row, column - 1)
                ).filter(this::isInMapRange)
                .collect(Collectors.toList());

    }

    public List<Entity> getOfType(Predicate<Entity> entityType) {
        return occupiedCells.values()
                .stream()
                .filter(entityType)
                .collect(Collectors.toList());
    }

    public long countOfType(Predicate<Entity> entityType) {
        return occupiedCells.values().stream().filter(entityType).count();
    }

    public void putEntity(Entity entity, Coordinate coordinate) {
        if (isEmptyCell(coordinate) && isInMapRange(coordinate)) {
            occupiedCells.put(coordinate, entity);
        } else {
            throw new IllegalArgumentException(String.format("Cannot put entity on coordinate %s", coordinate));
        }
    }

    public void removeEntityAt(Coordinate coordinate) {
        occupiedCells.remove(coordinate);
    }

    public boolean isEmptyCell(Coordinate coordinate) {
        return !occupiedCells.containsKey(coordinate);
    }

    private boolean isInMapRange(Coordinate coordinate) {
        return (coordinate.row() > -1 && coordinate.row() < height) && (coordinate.column() > -1 && coordinate.column() < width);
    }


}
