import action.Action;
import action.MoveEntityAction;
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
        entityCounter = new EntityCounter(5, 5, 3, 5);
        pathfinder = new Pathfinder(worldMap);
    }


    private SpawnEntityAction createSpawnEntityAction(Entity entity, Coordinate coordinate) {
        return new SpawnEntityAction(
                entity,
                coordinate,
                worldMap
        );
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
            Creature herbivore = new Herbivore(1, 2, Coordinate.getRandomCoordinate(worldMap.getWidth(), worldMap.getHeight()));
            addInitAction(createSpawnEntityAction(herbivore, herbivore.getCoordinate()));
            entityCounter.incrementHerbivoresCount();
        }
        while (entityCounter.getCurrentPredators() < entityCounter.getMaxPredators()) {
            Creature predator = new Predator(1, 2, 2, Coordinate.getRandomCoordinate(worldMap.getWidth(), worldMap.getHeight()));
            addInitAction(createSpawnEntityAction(predator, predator.getCoordinate()));
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
        mapRenderer.printMap();
        System.out.println();
        Action move = new MoveEntityAction(worldMap, pathfinder);
        addTurnAction(move);
        executeTurnActions();
        // render map
        mapRenderer.printMap();
    }

    public void start() {
        fillInitActions();
        executeInitActions();
        nextTurn();
//        Herbivore h = new Herbivore(1, 2, Coordinate.getRandomCoordinate(worldMap.getWidth(), worldMap.getHeight()));
//        worldMap.putEntity(h, h.getCoordinate());
//        List<Coordinate> result = pathfinder.findFood(h.getCoordinate(), e -> e instanceof Grass);
//        System.out.println();
//        mapRenderer.printMap();
//        System.out.println(result);
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

    private void executeTurnActions() {
        for (Action action : turnActions) {
            action.execute();
        }
    }
}
