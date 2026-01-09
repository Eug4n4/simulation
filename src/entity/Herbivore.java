package entity;

import worldmap.WorldMap;

public class Herbivore extends Creature implements Eatable {
    public Herbivore(int speed, int health) {
        super(speed, health);
    }

    public void damage(int damage) {
        setHealth(getHealth() - damage);
    }
    @Override
    public Class<? extends Eatable> getPossibleFood() {
        return Grass.class;
    }

    @Override
    protected boolean shouldStopAfterInteractionWith(Entity entity) {
        return false;
    }

    @Override
    protected void interactWith(Entity entity, WorldMap worldMap) {
        worldMap.removeEntityAt(worldMap.getEntityCoordinate(entity));
    }

}
