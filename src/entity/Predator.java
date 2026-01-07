package entity;

import worldmap.Pathfinder;
import worldmap.WorldMap;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Predator extends Creature {
    private final int force;
    public Predator(int speed, int health, int force) {
        super(speed, health);
        this.force = force;
    }

    public void attack(Herbivore target) {
        target.damage(force);
    }

    @Override
    public void makeMove(Pathfinder pathfinder, WorldMap worldMap) {
        Predicate<Entity> foodType = e -> e instanceof Herbivore;
        Coordinate myCoordinate = worldMap.getEntityCoordinate(this);
        List<Coordinate> routeToFood = pathfinder.findFood(myCoordinate, foodType);
        int cellCount = Math.min(getSpeed(), routeToFood.size());
        Iterator<Coordinate> iterator = routeToFood.iterator();

        for (int i = 0; i < cellCount; i++) {
            Coordinate coordinate = iterator.next();
            Optional<Entity> cell = worldMap.getEntityFromCell(coordinate);
            if (cell.isEmpty() || foodType.test(cell.get())) {
                if (cell.isPresent()) {
                    Herbivore creature = (Herbivore) cell.get();
                    attack(creature);
                    if (creature.isAlive()) {
                        break;
                    }
                    worldMap.removeEntityAt(coordinate);
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
}
