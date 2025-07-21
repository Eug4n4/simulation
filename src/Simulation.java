import action.Action;
import action.SpawnEntityAction;
import entity.*;
import worldmap.Pathfinder;
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
    private final Pathfinder pathfinder;

    public Simulation(WorldMap worldMap) {
        this.worldMap = worldMap;
        mapRenderer = new WorldMapRenderer(worldMap);
        entityCounter = new EntityCounter(5, 5, 3, 2);
        pathfinder = new Pathfinder(worldMap);
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
        while (entityCounter.getCurrentFood() < entityCounter.getMaxFood()) {
            addInitAction(createSpawnEntityAction(new Grass()));
            entityCounter.incrementFoodCount();
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
        Herbivore h = new Herbivore(1, 2);
        worldMap.putEntity(h, Coordinate.getRandomCoordinate(worldMap.getWidth(), worldMap.getHeight()));
        List<Coordinate> result = pathfinder.findFood(worldMap.getEntityCoordinate(h).orElse(new Coordinate(0, 0)), e -> e instanceof Grass);
        System.out.println();
        mapRenderer.printMap();
        System.out.println(result);
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
