import action.Action;
import action.SpawnEntityAction;
import entity.*;
import worldmap.WorldMap;
import worldmap.WorldMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Action> initActions = new ArrayList<>();
    private List<Action> turnActions = new ArrayList<>();
    private WorldMap worldMap;
    private final WorldMapRenderer mapRenderer;
    private final EntityCounter entityCounter;

    public Simulation(WorldMap worldMap) {
        this.worldMap = worldMap;
        mapRenderer = new WorldMapRenderer(worldMap);
        entityCounter = new EntityCounter(5, 5, 3, 2);
    }


    private SpawnEntityAction createSpawnEntityAction(Entity entity) {
        return new SpawnEntityAction(
                entity,
                Coordinate.getRandomCoordinate(worldMap.getWidth(), worldMap.getHeight()),
                worldMap
        );
    }

    private void fillInitActions() {
        while (entityCounter.getCurrentHerbivores() < entityCounter.getMaxHerbivores()) {
            addInitAction(createSpawnEntityAction(new Herbivore(1, 2)));
            entityCounter.incrementHerbivoresCount();
        }
        while (entityCounter.getCurrentPredators() < entityCounter.getMaxPredators()) {
            addInitAction(createSpawnEntityAction(new Predator(1, 2, 2)));
            entityCounter.incrementPredatorsCount();
        }
        while (entityCounter.getCurrentEat() < entityCounter.getMaxEat()) {
            addInitAction(createSpawnEntityAction(new Grass()));
            entityCounter.incrementEatCount();
        }
        while (entityCounter.getCurrentObstacles() < entityCounter.getMaxObstacles()) {
            addInitAction(createSpawnEntityAction(new Palm()));
            entityCounter.incrementObstaclesCount();
        }

    }

    private void nextTurn() {
        // executeTurnActions()
        // render map
        mapRenderer.printMap();
    }

    public void start() {
        fillInitActions();
        executeInitActions();
        nextTurn();
        /*
        while (true)
           nextTurn
                if pause
                pauseSimulation

         */


    }

    private void addInitAction(Action action) {
        initActions.add(action);
    }

    private void addTurnAction(Action action) {
        turnActions.add(action);
    }

    private void executeInitActions() {
        for (Action action : initActions) {
            action.execute();
        }
    }
}
