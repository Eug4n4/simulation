package entity;

import worldmap.Pathfinder;
import worldmap.WorldMap;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Herbivore extends Creature {
    public Herbivore(int speed, int health, Coordinate coordinate) {
        super(speed, health, coordinate);
    }

    public void damage(int damage) {
        setHealth(getHealth() - damage);
    }
    @Override
    public void makeMove(Pathfinder pathfinder, WorldMap worldMap) {
        if (!isAlive()) {
            return;
        }
        List<Coordinate> routeToFood = getRouteToFood();
        Predicate<Entity> foodType = e -> e instanceof Grass;
        if (routeToFood == null || routeToFood.isEmpty()) {
            setRouteToFood(pathfinder.findFood(getCoordinate(), foodType));
            routeToFood = getRouteToFood();
        }
        int cellCount = Math.min(getSpeed(), routeToFood.size());
        Iterator<Coordinate> iterator = routeToFood.iterator();
        System.out.printf("%s %s", "Herbivore: ", getCoordinate());
        for (int i = 0; i < cellCount; i++) {
            Coordinate coordinate = iterator.next();
            Optional<Entity> cell = worldMap.getEntityFromCell(coordinate);
            if (cell.isEmpty() || foodType.test(cell.get())) {
                worldMap.removeEntityAt(getCoordinate());
                setCoordinate(coordinate);
                worldMap.putEntity(this, getCoordinate());
                iterator.remove();
            }

        }
        System.out.printf(" %s\n", getCoordinate());
    }

}
