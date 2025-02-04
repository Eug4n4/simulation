package worldmap;

import entity.Sprite;

import java.util.ArrayList;
import java.util.List;

public class WorldMapRenderer {
    private WorldMap worldMap;
    private List<List<Sprite>> spritesMap = new ArrayList<>();

    public WorldMapRenderer(WorldMap worldMap) {
        this.worldMap = worldMap;
        initSpritesMap();
    }
    private void initSpritesMap() {
        int width = worldMap.getWidth();
        int height = worldMap.getHeight();

        for (int i = 0; i < height; i++) {
            List<Sprite> row = new ArrayList<>();
            spritesMap.add(row);
        }
        for (int i = 0; i < height; i++) {
            List<Sprite> row = spritesMap.get(i);

            for (int j = 0; j < width; j++) {
                row.add(Sprite.CELL);
            }
        }
    }

    public void printMap() {
        for (List<Sprite> list : spritesMap) {
            for (Sprite sprite : list) {
                System.out.print(Character.toString(sprite.getCodePoint()));
            }
            System.out.println();
        }
    }

}
