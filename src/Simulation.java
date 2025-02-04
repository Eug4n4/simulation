import worldmap.WorldMap;
import worldmap.WorldMapRenderer;

public class Simulation {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    private WorldMap worldMap = new WorldMap(WIDTH, HEIGHT);
    private final WorldMapRenderer mapRenderer = new WorldMapRenderer(worldMap);

    public void nextTurn() {
        /*
         render map
         do certain action
        */
    }

    public void start() {
        /*
        while (true)
           nextTurn
                if pause
                pauseSimulation

         */


    }
}
