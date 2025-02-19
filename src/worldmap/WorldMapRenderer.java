package worldmap;

import entity.Entity;
import entity.Sprite;

import java.util.ArrayList;
import java.util.List;

public class WorldMapRenderer {
    private final WorldMap worldMap;
//    private List<List<Sprite>> spritesMap = new ArrayList<>();
//    private List<Sprite> spritesMap = new ArrayList<>();
    public WorldMapRenderer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void printMap() {
        for (int i = 0; i < worldMap.getHeight(); i++) {

            for (int j = 0; j < worldMap.getWidth(); j++) {
                System.out.print(Character.toString(worldMap.getSpriteFromCell(i, j).getCodePoint()));
            }
            System.out.println();
        }
    }

}
