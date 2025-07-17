package entity;

public class EntityCounter {
    private final int maxHerbivores;
    private final int maxPredators;
    private final int maxFood;
    private final int maxObstacles;
    private int currentHerbivores;
    private int currentPredators;
    private int currentFood;
    private int currentObstacles;

    public EntityCounter(int max_herbivores, int max_predators, int max_food, int max_obstacles) {
        this.maxHerbivores = max_herbivores;
        this.maxPredators = max_predators;
        this.maxFood = max_food;
        this.maxObstacles = max_obstacles;
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

    public int getCurrentHerbivores() {
        return currentHerbivores;
    }

    public int getCurrentPredators() {
        return currentPredators;
    }

    public int getCurrentFood() {
        return currentFood;
    }

    public int getCurrentObstacles() {
        return currentObstacles;
    }

    public void incrementHerbivoresCount() {
        currentHerbivores++;
    }

    public void incrementPredatorsCount() {
        currentPredators++;
    }

    public void incrementFoodCount() {
        currentFood++;
    }

    public void incrementObstaclesCount() {
        currentObstacles++;
    }
}
