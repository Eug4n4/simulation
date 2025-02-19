package worldmap;


import entity.Entity;
import entity.Sprite;

import java.util.Optional;

public class WorldMapRenderer {
    private final WorldMap worldMap;

    public WorldMapRenderer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void printMap() {
        for (int i = 0; i < worldMap.getHeight(); i++) {

            for (int j = 0; j < worldMap.getWidth(); j++) {
                Optional<Entity> entityToPrint = worldMap.getEntityFromCell(i, j);

                entityToPrint.ifPresentOrElse(entity ->
                        System.out.print(Character.toString(Sprite.getSpriteFromEntity(entity).getCodePoint())),
                        () -> System.out.print(Character.toString(Sprite.CELL.getCodePoint()))
                );

            }
            System.out.println();
        }
    }

}
