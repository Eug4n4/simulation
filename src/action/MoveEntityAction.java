package action;

import entity.Creature;
import entity.Entity;
import worldmap.Pathfinder;
import worldmap.WorldMap;

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
        List<Entity> creatures = worldMap.getOfType(e -> e instanceof Creature);
        for (Entity creature : creatures) {
            ((Creature)creature).makeMove(pathfinder, worldMap);
        }
    }
}
