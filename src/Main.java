import action.Action;
import action.MoveEntityAction;
import action.SpawnEntityAction;
import dialog.Dialog;
import dialog.ScannerIntegerConsoleDialog;
import entity.*;
import validator.MinMaxValidator;
import validator.Validator;
import worldmap.Pathfinder;
import worldmap.WorldMap;
import worldmap.WorldMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int HERBIVORE_SPEED = 2;
    private static final int HERBIVORE_HEALTH = 2;
    private static final int PREDATOR_SPEED = 1;
    private static final int PREDATOR_HEALTH = 2;
    private static final int PREDATOR_FORCE = 2;
    private static final int MIN_WORLD_DIMENSION = 10;
    private static final int MAX_WORLD_DIMENSION = 50;

    public static void main(String[] args) {
        WorldMap worldMap = getWorldMap();
        int maxHerbivores = Math.max(worldMap.getWidth(), worldMap.getHeight()) / 2;
        int maxPredators = maxHerbivores + 1;
        int maxFood = maxHerbivores - 1;
        int maxObstacles = maxHerbivores * 2;
        Pathfinder pathfinder = new Pathfinder(worldMap);
        EntityCounter counter = new EntityCounter(maxHerbivores, maxPredators, maxFood, maxObstacles);
        List<Action> initActions = new ArrayList<>();


        for (int i = 0; i < counter.maxHerbivores(); i++) {
            initActions.add(new SpawnEntityAction(() -> new Herbivore(HERBIVORE_SPEED, HERBIVORE_HEALTH), worldMap));
        }
        for (int i = 0; i < counter.maxPredators(); i++) {
            initActions.add(new SpawnEntityAction(() -> new Predator(PREDATOR_SPEED, PREDATOR_HEALTH, PREDATOR_FORCE), worldMap));

        }
        for (int i = 0; i < counter.maxFood(); i++) {
            initActions.add(new SpawnEntityAction(Grass::new, worldMap));
        }
        for (int i = 0; i < counter.maxObstacles(); i++) {
            initActions.add(new SpawnEntityAction(Palm::new, worldMap));
        }

        List<Action> turnActions = List.of(
                new MoveEntityAction(worldMap, pathfinder),
                new SpawnEntityAction(Grass::new, worldMap),
                new SpawnEntityAction(() -> new Herbivore(HERBIVORE_SPEED, HERBIVORE_HEALTH), worldMap)
        );
        turnActions.forEach(a -> {
            if (a instanceof SpawnEntityAction spawn) {
                spawn.setCounter(counter);
            }
        });

        Simulation simulation = new Simulation(new WorldMapRenderer(worldMap), initActions, turnActions);
        simulation.start();
    }

    private static WorldMap getWorldMap() {
        Validator<Integer> dimensionValidator = new MinMaxValidator(MIN_WORLD_DIMENSION, MAX_WORLD_DIMENSION, String.format("Map can't be smaller than %dx%d and bigger than %dx%d", MIN_WORLD_DIMENSION, MIN_WORLD_DIMENSION, MAX_WORLD_DIMENSION, MAX_WORLD_DIMENSION));
        Dialog<Integer> mapWidth = new ScannerIntegerConsoleDialog("Enter map width:", "Width must be integer", dimensionValidator);
        Dialog<Integer> mapHeight = new ScannerIntegerConsoleDialog("Enter map height:", "Height must be integer", dimensionValidator);
        int width = mapWidth.input();
        int height = mapHeight.input();
        return new WorldMap(width, height);
    }
}