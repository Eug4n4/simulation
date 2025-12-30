package entity;

public class EntityCounter {
    private final int maxHerbivores;
    private final int maxPredators;
    private final int maxFood;
    private final int maxObstacles;

    public EntityCounter(int maxHerbivores, int maxPredators, int maxFood, int maxObstacles) {
        this.maxHerbivores = maxHerbivores;
        this.maxPredators = maxPredators;
        this.maxFood = maxFood;
        this.maxObstacles = maxObstacles;
    }

    public int getMaxHerbivores() {
        return maxHerbivores;
    }

    public int getMaxPredators() {
        return maxPredators;
    }

    public int getMaxFood() {
        return maxFood;
    }

    public int getMaxObstacles() {
        return maxObstacles;
    }

}
