import action.Action;
import action.SpawnEntityAction;
import entity.Coordinate;
import entity.Entity;
import entity.Herbivore;
import entity.Sprite;
import worldmap.WorldMap;
import worldmap.WorldMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    List<Action> initActions = new ArrayList<>();
    List<Action> turnActions = new ArrayList<>();
    private WorldMap worldMap = new WorldMap(WIDTH, HEIGHT);
    private final WorldMapRenderer mapRenderer = new WorldMapRenderer(worldMap);

    public void nextTurn() {
        // executeTurnActions()
        // render map
        mapRenderer.printMap();
    }

    public void start() {
        Entity herbivore = new Herbivore(5,4,1, 2, Sprite.HERBIVORE);
        addInitAction(new SpawnEntityAction(herbivore, Coordinate.getRandomCoordinate(WIDTH, HEIGHT), worldMap));
        executeInitActions();
        nextTurn();
        /*
        while (true)
           nextTurn
                if pause
                pauseSimulation

         */


    }

    public void addInitAction(Action action) {
        initActions.add(action);
    }

    public void addTurnAction(Action action) {
        turnActions.add(action);
    }

    public void executeInitActions() {
        for (Action action : initActions) {
            action.execute();
        }
    }
}
