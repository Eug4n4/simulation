import action.Action;
import action.MoveEntityAction;
import action.SpawnEntityAction;
import entity.Coordinate;
import entity.Herbivore;
import entity.Palm;
import entity.Predator;
import worldmap.Pathfinder;
import worldmap.WorldMap;
import worldmap.WorldMapRenderer;

import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap(10, 12);
        Pathfinder pathfinder = new Pathfinder(worldMap);
        Action[] spawnActions = {
                new SpawnEntityAction(new Herbivore(1, 2), new Coordinate(6,7), worldMap),
                new SpawnEntityAction(new Predator(1, 2, 3), new Coordinate(0, 8), worldMap),
                new SpawnEntityAction(new Palm(), new Coordinate(1, 9), worldMap),

        };
        Action[] turnActionsArr = {
                new MoveEntityAction(worldMap, pathfinder)
        };
        List<Action> initActions = Arrays.asList(spawnActions);
        List<Action> turnActions = Arrays.asList(turnActionsArr);

        Simulation simulation = new Simulation(new WorldMapRenderer(worldMap), initActions, turnActions);
        simulation.start();
    }
}