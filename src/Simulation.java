import action.Action;
import entity.*;
import worldmap.WorldMapRenderer;

import java.util.List;

public class Simulation {
    private final List<Action> turnActions;
//    private final WorldMap worldMap;
    private final WorldMapRenderer mapRenderer;
    private final EntityCounter entityCounter;
//    private final Pathfinder pathfinder;

    public Simulation(WorldMapRenderer renderer, List<Action> initActions, List<Action> turnActions) {
//        this.worldMap = worldMap;
//        this.pathfinder = pathfinder;
        this.turnActions = turnActions;
        mapRenderer = renderer;
        entityCounter = new EntityCounter(5, 5, 3, 5);
        executeActions(initActions);
    }



    private void nextTurn() {
        System.out.println();
        executeActions(turnActions);
        mapRenderer.printMap();
    }

    public void start() {
        mapRenderer.printMap();
        for (int i = 0; i < 2; i++) {
            nextTurn();
        }

        /*
        while (true)
           nextTurn
                if pause
                pauseSimulation

         */


    }



    private void executeActions(List<Action> container) {
        for (Action action : container) {
            action.execute();
        }
    }

}
