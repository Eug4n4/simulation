package worldmap;

import entity.Coordinate;
import entity.Creature;
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


    public boolean isEmptyCell(Coordinate coordinate) {
        return !occupiedCells.containsKey(coordinate);
    }

    public void putEntity(Entity entity, Coordinate coordinate) {
        if (isEmptyCell(coordinate) && isInMapRange(coordinate)) {
            occupiedCells.put(coordinate, entity);
        } else {
            throw new IllegalArgumentException(String.format("Cannot put entity on coordinate row = %d column = %d", coordinate.row(), coordinate.column()));
        }
    }

    public void removeEntityAt(Coordinate coordinate) {
        occupiedCells.remove(coordinate);
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

    public Optional<Entity> getEntityFromCell(Coordinate coordinate) {

        if (!isEmptyCell(coordinate)) {
            return Optional.of(occupiedCells.get(coordinate));
        }
        return Optional.empty();
    }

    public Coordinate getEntityCoordinate(Entity entity) {
        return occupiedCells.keySet()
                .stream()
                .filter(coordinate -> occupiedCells.get(coordinate).equals(entity))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public List<Entity> getOfType(Predicate<Entity> entityType) {
        return occupiedCells.values()
                .stream()
                .filter(entityType)
                .collect(Collectors.toList());
    }

    private boolean isInMapRange(Coordinate coordinate) {
        return (coordinate.row() > -1 && coordinate.row() < height) && (coordinate.column() > -1 && coordinate.column() < width);
    }


}
