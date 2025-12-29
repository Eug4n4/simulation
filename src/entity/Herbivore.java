package entity;

import worldmap.Pathfinder;
import worldmap.WorldMap;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Herbivore extends Creature {
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
        List<Coordinate> routeToFood = getRouteToFood();
        Predicate<Entity> foodType = e -> e instanceof Grass;
        if (routeToFood == null || routeToFood.isEmpty()) {
            setRouteToFood(pathfinder.findFood(myCoordinate, foodType));
            routeToFood = getRouteToFood();
        }
        int cellCount = Math.min(getSpeed(), routeToFood.size());
        Iterator<Coordinate> iterator = routeToFood.iterator();
        System.out.printf("%s %s", "Herbivore: ", myCoordinate);
        for (int i = 0; i < cellCount; i++) {
            Coordinate coordinate = iterator.next();
            Optional<Entity> cell = worldMap.getEntityFromCell(coordinate);
            if (cell.isEmpty() || foodType.test(cell.get())) {
                worldMap.removeEntityAt(myCoordinate);
                worldMap.putEntity(this, coordinate);
                iterator.remove();
            }

        }
        System.out.printf(" %s\n", worldMap.getEntityCoordinate(this));
    }

}
