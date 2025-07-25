package entity;

import worldmap.Pathfinder;
import worldmap.WorldMap;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Predator extends Creature {
    private int force;

    public Predator(int speed, int health, int force, Coordinate coordinate) {
        super(speed, health, coordinate);
        this.force = force;
    }

    public void attack(Herbivore target) {
        target.damage(force);
    }

    @Override
    public void makeMove(Pathfinder pathfinder, WorldMap worldMap) {
        List<Coordinate> routeToFood = getRouteToFood();
        Predicate<Entity> foodType = e -> e instanceof Herbivore;
        if (routeToFood == null || routeToFood.isEmpty()) {
            setRouteToFood(pathfinder.findFood(getCoordinate(), foodType));
            routeToFood = getRouteToFood();
        }
        int cellCount = Math.min(getSpeed(), routeToFood.size());
        Iterator<Coordinate> iterator = routeToFood.iterator();
        System.out.printf("%s %s", "Predator: ", getCoordinate());

        for (int i = 0; i < cellCount; i++) {
            Coordinate coordinate = iterator.next();
            Optional<Entity> cell = worldMap.getEntityFromCell(coordinate);
            if (cell.isEmpty()) {
                worldMap.removeEntityAt(getCoordinate());
                setCoordinate(coordinate);
                worldMap.putEntity(this, getCoordinate());
                iterator.remove();

            } else {
                if (foodType.test(cell.get())) {
                    Herbivore creature = (Herbivore) cell.get();
                    attack(creature);
                    if (creature.isAlive()) {
                        break;
                    } else {
                        worldMap.removeEntityAt(getCoordinate());
                        setCoordinate(coordinate);
                        worldMap.updateEntity(this, coordinate);
                        iterator.remove();
                    }
                } else {
                    break;
                }
            }
        }
        System.out.printf(" %s\n", getCoordinate());
    }
}
