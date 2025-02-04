package entity;

public class Predator extends Creature {
    int force;

    public Predator(int x, int y, int speed, int health, int force, Sprite sprite) {
        super(x, y, speed, health, sprite);
        this.force = force;
    }

    void attack() {

    }
}
