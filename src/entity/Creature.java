package entity;

import worldmap.Pathfinder;
import worldmap.WorldMap;

import java.util.List;

abstract public class Creature extends Entity {
    private final int speed;
    private int health;
    private List<Coordinate> routeToFood;


    public Creature(int speed, int health) {
        this.speed = speed;
        this.health = health;
    }

    public abstract void makeMove(Pathfinder pathfinder, WorldMap worldMap);

    public List<Coordinate> getRouteToFood() {
        return routeToFood;
    }

    public void setRouteToFood(List<Coordinate> routeToFood) {
        this.routeToFood = routeToFood;
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
