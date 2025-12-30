import action.Action;
import action.MoveEntityAction;
import action.SpawnEntityAction;
import entity.*;
import worldmap.Pathfinder;
import worldmap.WorldMap;
import worldmap.WorldMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap(10, 12);
        Pathfinder pathfinder = new Pathfinder(worldMap);
        EntityCounter counter = new EntityCounter(5, 5, 3, 5);
        List<Action> initActions = new ArrayList<>();


        for (int i = 0; i < counter.getMaxHerbivores(); i++) {
            initActions.add(new SpawnEntityAction(new Herbivore(1, 2), worldMap));
        }
        for (int i = 0; i < counter.getMaxPredators(); i++) {
            initActions.add(new SpawnEntityAction(new Predator(1, 2, 3), worldMap));

        }
        for (int i = 0; i < counter.getMaxFood(); i++) {
            initActions.add(new SpawnEntityAction(new Grass(), worldMap));
        }
        for (int i = 0; i < counter.getMaxObstacles(); i++) {
            initActions.add(new SpawnEntityAction(new Palm(), worldMap));
        }

        List<Action> turnActions = List.of(
                new MoveEntityAction(worldMap, pathfinder),
                new SpawnEntityAction(new Grass(), worldMap),
                new SpawnEntityAction(new Herbivore(1, 2), worldMap)
        );
        turnActions.forEach(a -> {
            if (a instanceof SpawnEntityAction spawn) {
                spawn.setCounter(counter);
            }
        });

        Simulation simulation = new Simulation(new WorldMapRenderer(worldMap), initActions, turnActions);
        simulation.start();
    }
}