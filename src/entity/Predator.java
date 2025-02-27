package entity;

public class Predator extends Creature {
    private int force;

    public Predator(int speed, int health, int force) {
        super(speed, health);
        this.force = force;
    }

    void attack() {

    }
}
