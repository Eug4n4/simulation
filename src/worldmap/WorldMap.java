package worldmap;

import entity.Coordinate;
import entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        if (isEmptyCell(coordinate)) {
            occupiedCells.put(coordinate, entity);
        }
    }

    public List<Coordinate> getAdjacentCoordinates(Coordinate coordinate) {
        int row = coordinate.getX();
        int column = coordinate.getY();
        return Stream.of(new Coordinate(row - 1, column - 1),
                        new Coordinate(row - 1, column),
                        new Coordinate(row - 1, column + 1),
                        new Coordinate(row, column + 1),
                        new Coordinate(row + 1, column + 1),
                        new Coordinate(row + 1, column),
                        new Coordinate(row + 1, column - 1),
                        new Coordinate(row, column - 1)
                ).filter(coord -> coord.getX() > -1 && coord.getX() < width && coord.getY() > -1 && coord.getY() < height)
                .collect(Collectors.toList());

    }

    public final Optional<Entity> getEntityFromCell(Coordinate coordinate) {

        if (!isEmptyCell(coordinate)) {
            return Optional.of(occupiedCells.get(coordinate));
        }
        return Optional.empty();
    }

    public Optional<Coordinate> getEntityCoordinate(Entity entity) {
        return occupiedCells.keySet()
                .stream()
                .filter(coordinate -> occupiedCells.get(coordinate).equals(entity))
                .findFirst();
    }


}
