package entity;

import worldmap.Pathfinder;
import worldmap.WorldMap;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Herbivore extends Creature implements Cloneable {
    public Herbivore(int speed, int health) {
        super(speed, health);
    }

    public void damage(int damage) {
        setHealth(getHealth() - damage);
    }
    @Override
    public void makeMove(Pathfinder pathfinder, WorldMap worldMap) {
        if (!isAlive()) {
            return;
        }
        Coordinate myCoordinate = worldMap.getEntityCoordinate(this);
        Predicate<Entity> foodType = e -> e instanceof Grass;
        List<Coordinate> routeToFood = pathfinder.findFood(myCoordinate, foodType);
        int cellCount = Math.min(getSpeed(), routeToFood.size());
        Iterator<Coordinate> iterator = routeToFood.iterator();
        System.out.printf("%s %s", "Herbivore: ", myCoordinate);
        for (int i = 0; i < cellCount; i++) {
            Coordinate coordinate = iterator.next();
            Optional<Entity> cell = worldMap.getEntityFromCell(coordinate);
            if (cell.isEmpty() || foodType.test(cell.get())) {
                if (cell.isPresent()) {
                    worldMap.removeEntityAt(coordinate);
                }
                worldMap.removeEntityAt(myCoordinate);
                System.out.printf(" %s %s\n", "Herbivore: ", coordinate);
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
