import action.Action;
import dialog.Dialog;
import dialog.ScannerIntegerConsoleDialog;
import validator.MinMaxValidator;
import worldmap.WorldMapRenderer;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Simulation {
    private final List<Action> turnActions;
    private final WorldMapRenderer mapRenderer;
    private long turnCount = 0;
    private boolean isRunning = false;
    private final BlockingQueue<Integer> options = new ArrayBlockingQueue<>(1);

    public Simulation(WorldMapRenderer renderer, List<Action> initActions, List<Action> turnActions) {
        this.turnActions = turnActions;
        mapRenderer = renderer;
        executeActions(initActions);
    }

    public void start() {
        System.out.println("Initial map:");
        mapRenderer.printMap();
        Dialog<Integer> input = new ScannerIntegerConsoleDialog("""
                1 - Start endless simulation
                2 - Pause endless simulation
                3 - Simulate single turn
                4 - Quit""", "Invalid option", new MinMaxValidator(1, 4, "Invalid option"));
        Thread inputListener = new Thread(new InputListener(input));
        Thread runner = new Thread(new Runner());
        runner.setName("Runner");
        inputListener.start();
        runner.start();
    }

    private void startSimulation() {
        isRunning = true;
        while (isRunning) {
            nextTurn();
        }

    }

    private void pauseSimulation() {
        isRunning = false;
    }

    private void nextTurn() {
        turnCount++;
        System.out.printf("Turn %d:\n", turnCount);
        executeActions(turnActions);
        mapRenderer.printMap();
    }


    private void executeActions(List<Action> container) {
        for (Action action : container) {
            action.execute();
        }
    }

    private class InputListener implements Runnable {
        private final Dialog<Integer> input;

        public InputListener(Dialog<Integer> input) {
            this.input = input;
        }

        @Override
        public void run() {
            int option = -1;

            while (option != 4) {
                try {
                    option = input.input();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
                if (option == 2) {
                    pauseSimulation();
                } else if ((!isRunning && (option == 1 || option == 3)) || option == 4) {
                    if (option == 4) {
                        pauseSimulation();
                    }
                    options.add(option);
                }

            }


        }
    }

    private class Runner implements Runnable {
        @Override
        public void run() {

            int option = -1;
            while (option != 4) {
                try {
                    option = options.take();
                    if (option == 1) {
                        startSimulation();
                    } else if (option == 3) {
                        nextTurn();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
