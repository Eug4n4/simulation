package worldmap;

import entity.Coordinate;
import entity.Entity;
import java.util.Map;

public class WorldMap {
    private final int width;
    private final int height;
    private Map<Coordinate, Entity> occupiedCells;

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

    public Map<Coordinate, Entity> getOccupiedCells() {
        return occupiedCells;
    }


}
