package action;

import entity.*;
import worldmap.WorldMap;

import java.util.function.Supplier;

public class SpawnEntityAction implements Action {
    private final Supplier<Entity> supplier;
    private final WorldMap worldMap;
    private EntityCounter counter;

    public SpawnEntityAction(Supplier<Entity> supplier, WorldMap worldMap) {
        this.supplier = supplier;
        this.worldMap = worldMap;
    }

    @Override
    public void execute() {
        Coordinate random = worldMap.getRandomEmptyCoordinate();
        Entity entity = supplier.get();
        if (counter != null) {
            long herbivores = worldMap.countOfType(e -> e instanceof Herbivore);
            long grasses = worldMap.countOfType(e -> e instanceof Grass);

            if (entity instanceof Grass && grasses == counter.maxFood()) {
                return;
            }

            if (entity instanceof Herbivore && herbivores == counter.maxHerbivores()) {
                return;
            }
        }
        worldMap.putEntity(entity, random);

    }

    public void setCounter(EntityCounter counter) {
        this.counter = counter;
    }

}
