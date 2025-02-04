package entity;

abstract public class Creature extends Entity {
    private int speed;
    private int health;

    public Creature(int x, int y, int speed, int health, Sprite sprite) {
        super(x, y, sprite);
        this.speed = speed;
        this.health = health;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
