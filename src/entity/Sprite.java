package entity;

public enum Sprite {
    CELL(127999),
    PREDATOR(129409),
    HERBIVORE(129427),
    GRASS(127808);

    private final int codePoint;

    Sprite(int codePoint) {
        this.codePoint = codePoint;
    }

    public static Sprite getSpriteFromEntity(Entity entity) {
        return switch (entity) {
            case Herbivore _ -> HERBIVORE;
            case Predator _ -> PREDATOR;
            case Grass _ -> GRASS;
            case null, default -> CELL;
        };
    }

    public int getCodePoint() {
        return codePoint;
    }

}
