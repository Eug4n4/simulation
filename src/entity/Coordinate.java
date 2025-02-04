package entity;

import java.util.Random;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Coordinate getRandomCoordinate(int maxX, int maxY) {
        Random random = new Random();
        return new Coordinate(random.nextInt(maxX), random.nextInt(maxY));
    }
}
