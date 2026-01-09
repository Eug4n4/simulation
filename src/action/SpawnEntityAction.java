package action;

import entity.*;
import worldmap.WorldMap;

public class SpawnEntityAction implements Action {
    private Entity entity;
    private final int initialHealth;
    private final WorldMap worldMap;
    private EntityCounter counter;

    public SpawnEntityAction(Entity entity, WorldMap worldMap) {
        this.entity = entity;
        this.worldMap = worldMap;
        initialHealth = entity instanceof Creature c ? c.getHealth() : 0;
    }

    @Override
    public void execute() {
        Coordinate random = worldMap.getRandomEmptyCoordinate();
        if (counter != null) {
            long herbivores = worldMap.countOfType(e -> e instanceof Herbivore);
            long grasses = worldMap.countOfType(e -> e instanceof Grass);

            if (entity instanceof Grass && grasses == counter.maxFood()) {
                return;
            }

            if (entity instanceof Herbivore h) {
                if (herbivores == counter.maxHerbivores()) {
                    return;
                } else if (herbivores < counter.maxHerbivores()) {
                    h.setHealth(initialHealth);
                    entity = h.clone();
                }
            }

        }
        worldMap.putEntity(entity, random);

    }

    public void setCounter(EntityCounter counter) {
        this.counter = counter;
    }

}
