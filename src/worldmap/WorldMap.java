package worldmap;

import entity.Coordinate;
import entity.Entity;
import entity.Sprite;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private final int width;
    private final int height;
    private Map<Coordinate, Entity> occupiedCells;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        occupiedCells = new HashMap<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public final Sprite getSpriteFromCell(int row, int column) {
        Coordinate coordinate = new Coordinate(row, column);

        if (occupiedCells.containsKey(coordinate)) {
            return occupiedCells.get(coordinate).getSprite();
        }
        return Sprite.CELL;
    }


}
