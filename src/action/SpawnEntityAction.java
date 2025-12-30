package action;

import entity.*;
import worldmap.WorldMap;

import java.util.function.Supplier;

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
        Supplier<Coordinate> randomCoordinate = worldMap.getRandomEmptyCoordinate();
        if (counter != null) {
            long herbivores = worldMap.countOfType(Herbivore.class);
            long grasses = worldMap.countOfType(Grass.class);
            if (herbivores == counter.getMaxHerbivores() && grasses == counter.getMaxFood()) {
                return;
            }
            if (entity instanceof Herbivore h && herbivores < counter.getMaxHerbivores()) {
                h.setHealth(initialHealth);
                entity = h.clone();
            } else if (entity instanceof Grass && grasses == counter.getMaxFood()) {
                return;
            }

        }
        worldMap.putEntity(entity, randomCoordinate.get());

    }

    public void setCounter(EntityCounter counter) {
        this.counter = counter;
    }

}
