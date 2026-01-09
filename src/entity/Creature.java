package entity;

import worldmap.Pathfinder;
import worldmap.WorldMap;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

abstract public class Creature extends Entity {
    private final int speed;
    private int health;

    public Creature(int speed, int health) {
        this.speed = speed;
        this.health = health;
    }

    public abstract Class<? extends Eatable> getPossibleFood();

    protected abstract boolean shouldStopAfterInteractionWith(Entity entity);

    protected abstract void interactWith(Entity entity, WorldMap worldMap);

    public void makeMove(Pathfinder pathfinder, WorldMap worldMap) {
        if (!isAlive()) {
            return;
        }
        var foodType = getPossibleFood();
        Coordinate myCoordinate = worldMap.getEntityCoordinate(this);
        List<Coordinate> routeToFood = pathfinder.findFood(myCoordinate, foodType);
        int cellCount = Math.min(getSpeed(), routeToFood.size());
        Iterator<Coordinate> iterator = routeToFood.iterator();
        for (int i = 0; i < cellCount; i++) {
            Coordinate coordinate = iterator.next();
            Optional<Entity> cell = worldMap.getEntityFromCell(coordinate);
            if (cell.isEmpty() || getPossibleFood().isInstance(cell.get())) {
                if (cell.isPresent()) {
                    interactWith(cell.get(), worldMap);
                    if (shouldStopAfterInteractionWith(cell.get())) {
                        break;
                    }
                }
                worldMap.removeEntityAt(myCoordinate);
                worldMap.putEntity(this, coordinate);
                myCoordinate = coordinate;
                iterator.remove();
            } else {
                break;
            }
        }
    }


    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
