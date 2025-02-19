package entity;

abstract public class Creature extends Entity {
    private int speed;
    private int health;


    public Creature(int speed, int health) {
        this.speed = speed;
        this.health = health;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
