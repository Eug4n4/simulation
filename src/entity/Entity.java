package entity;

abstract public class Entity {
    private Coordinate coordinate;
    private final Sprite sprite;

    public Entity(int x, int y, Sprite sprite) {
        coordinate = new Coordinate(x, y);
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
