package entity;

public enum Sprite {
    CELL(127999),
    PREDATOR(129409),
    HERBIVORE(129427),
    GRASS(127808);

    private final int codePoint;

    private Sprite(int codePoint) {
        this.codePoint = codePoint;
    }

    public int getCodePoint() {
        return codePoint;
    }

}
