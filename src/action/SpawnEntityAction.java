package action;

import entity.Coordinate;
import entity.Entity;
import worldmap.WorldMap;

public class SpawnEntityAction extends Action {
    private final Entity entity;
    private final Coordinate coordinate;
    private final WorldMap worldMap;

    public SpawnEntityAction(Entity entity, Coordinate coordinate, WorldMap worldMap) {
        this.entity = entity;
        this.coordinate = coordinate;
        this.worldMap = worldMap;
    }

    private void spawnEntity() {
        worldMap.putEntity(entity, coordinate);
    }

    @Override
    public void execute() {
        spawnEntity();
    }
}
