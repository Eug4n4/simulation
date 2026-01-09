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
    private final BlockingQueue<Integer> options = new ArrayBlockingQueue<>(1);
    private long turnCount = 0;
    private boolean isRunning = false;
    private final int OPTION_START_ENDLESS_SIMULATION = 1;
    private final int OPTION_PAUSE_ENDLESS_SIMULATION = 2;
    private final int OPTION_SIMULATE_SINGLE_TURN = 3;
    private final int OPTION_QUIT = 4;

    public Simulation(WorldMapRenderer renderer, List<Action> initActions, List<Action> turnActions) {
        this.turnActions = turnActions;
        mapRenderer = renderer;
        executeActions(initActions);
    }

    public void start() {
        System.out.println("Initial map:");
        mapRenderer.printMap();
        Dialog<Integer> input = new ScannerIntegerConsoleDialog(
                String.format("""
                                %d - Start endless simulation
                                %d - Pause endless simulation
                                %d - Simulate single turn
                                %d - Quit""",
                        OPTION_START_ENDLESS_SIMULATION,
                        OPTION_PAUSE_ENDLESS_SIMULATION,
                        OPTION_SIMULATE_SINGLE_TURN,
                        OPTION_QUIT),
                "Invalid option",
                new MinMaxValidator(OPTION_START_ENDLESS_SIMULATION, OPTION_QUIT, "Invalid option"));
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

            while (option != OPTION_QUIT) {
                try {
                    option = input.input();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                    continue;
                }
                if (option == OPTION_PAUSE_ENDLESS_SIMULATION) {
                    pauseSimulation();
                } else if ((!isRunning && (option == OPTION_START_ENDLESS_SIMULATION || option == OPTION_SIMULATE_SINGLE_TURN)) || option == OPTION_QUIT) {
                    if (option == OPTION_QUIT) {
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
            while (option != OPTION_QUIT) {
                try {
                    option = options.take();
                    if (option == OPTION_START_ENDLESS_SIMULATION) {
                        startSimulation();
                    } else if (option == OPTION_SIMULATE_SINGLE_TURN) {
                        nextTurn();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
