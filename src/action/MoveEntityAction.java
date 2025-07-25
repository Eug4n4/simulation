package action;

import entity.Coordinate;
import entity.Creature;
import worldmap.Pathfinder;
import worldmap.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class MoveEntityAction implements Action {
    private final WorldMap worldMap;
    private final Pathfinder pathfinder;
    public MoveEntityAction(WorldMap worldMap, Pathfinder pathfinder) {
        this.worldMap = worldMap;
        this.pathfinder = pathfinder;
    }

    @Override
    public void execute() {
        List<Creature> creatures = worldMap.getCreatures();
        creatures.forEach(c -> c.makeMove(pathfinder, worldMap));
    }
}
