import action.Action;
import worldmap.WorldMapRenderer;

import java.util.List;

public class Simulation {
    private final List<Action> turnActions;
    private final WorldMapRenderer mapRenderer;
    private long turnCount = 0;

    public Simulation(WorldMapRenderer renderer, List<Action> initActions, List<Action> turnActions) {
        this.turnActions = turnActions;
        mapRenderer = renderer;
        executeActions(initActions);
    }



    private void nextTurn() {
        System.out.printf("Turn %d:\n", turnCount);
        executeActions(turnActions);
        mapRenderer.printMap();
    }

    public void start() {
        System.out.println("Initial map:");
        mapRenderer.printMap();
        for (int i = 0; i < 10; i++) {
            turnCount++;
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
