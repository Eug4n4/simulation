package entity;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
