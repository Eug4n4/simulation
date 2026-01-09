package entity;

import worldmap.Pathfinder;
import worldmap.WorldMap;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Herbivore extends Creature implements Eatable, Cloneable {
    public Herbivore(int speed, int health) {
        super(speed, health);
    }

    public void damage(int damage) {
        setHealth(getHealth() - damage);
    }
    @Override
    public Class<? extends Eatable> getPossibleFood() {
        return Grass.class;
    }

    @Override
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
            if (cell.isEmpty() || foodType.isInstance(cell.get())) {
                if (cell.isPresent()) {
                    worldMap.removeEntityAt(coordinate);
                }
                worldMap.removeEntityAt(myCoordinate);
                worldMap.putEntity(this, coordinate);
                myCoordinate = coordinate;
                iterator.remove();
            }

        }

    }

    @Override
    public Herbivore clone() {
        try {
            return (Herbivore) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
