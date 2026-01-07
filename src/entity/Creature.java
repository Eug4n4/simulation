package entity;

import worldmap.Pathfinder;
import worldmap.WorldMap;

abstract public class Creature extends Entity {
    private final int speed;
    private int health;

    public Creature(int speed, int health) {
        this.speed = speed;
        this.health = health;
    }

    public abstract void makeMove(Pathfinder pathfinder, WorldMap worldMap);

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
