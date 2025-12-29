package entity;

import java.util.Random;

public record Coordinate(int row, int column) {

    public static Coordinate getRandomCoordinate(int maxRow, int maxColumn) {
        Random random = new Random();
        return new Coordinate(random.nextInt(maxRow), random.nextInt(maxColumn));
    }
}
