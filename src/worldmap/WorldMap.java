package worldmap;

import entity.Coordinate;
import entity.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WorldMap {
    private final int width;
    private final int height;
    private Map<Coordinate, Entity> occupiedCells = new HashMap<>();

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

    public final Optional<Entity> getEntityFromCell(int row, int column) {
        Coordinate coordinate = new Coordinate(row, column);

        if (!isEmptyCell(coordinate)) {
            return Optional.of(occupiedCells.get(coordinate));
        }
        return Optional.empty();
    }


}
