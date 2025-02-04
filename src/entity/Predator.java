package entity;

public class Predator extends Creature {
    int force;

    public Predator(int x, int y, int speed, int health, int force) {
        super(x, y, speed, health);
        this.force = force;
    }

    void attack() {

    }
}
