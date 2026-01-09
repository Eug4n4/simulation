package entity;

import worldmap.WorldMap;

public class Predator extends Creature {
    private final int force;
    public Predator(int speed, int health, int force) {
        super(speed, health);
        this.force = force;
    }

    public void attack(Herbivore target) {
        target.damage(force);
    }

    @Override
    public Class<? extends Eatable> getPossibleFood() {
        return Herbivore.class;
    }

    @Override
    protected boolean shouldStopAfterInteractionWith(Entity entity) {
        return ((Creature)entity).isAlive();
    }

    @Override
    protected void interactWith(Entity entity, WorldMap worldMap) {
        Herbivore herbivore = (Herbivore) entity;
        attack(herbivore);
        if (!herbivore.isAlive()) {
            worldMap.removeEntityAt(worldMap.getEntityCoordinate(herbivore));
        }
    }

}
