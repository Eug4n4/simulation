package entity;

public class EntityCounter {
    private final int max_herbivores;
    private final int max_predators;
    private final int max_eat;
    private final int max_obstacles;
    private int current_herbivores;
    private int current_predators;
    private int current_eat;
    private int current_obstacles;

    public EntityCounter(int max_herbivores, int max_predators, int max_eat, int max_obstacles) {
        this.max_herbivores = max_herbivores;
        this.max_predators = max_predators;
        this.max_eat = max_eat;
        this.max_obstacles = max_obstacles;
    }

    public int getMaxHerbivores() {
        return max_herbivores;
    }

    public int getMaxPredators() {
        return max_predators;
    }

    public int getMaxEat() {
        return max_eat;
    }

    public int getMaxObstacles() {
        return max_obstacles;
    }

    public int getCurrentHerbivores() {
        return current_herbivores;
    }

    public int getCurrentPredators() {
        return current_predators;
    }

    public int getCurrentEat() {
        return current_eat;
    }

    public int getCurrentObstacles() {
        return current_obstacles;
    }

    public void incrementHerbivoresCount() {
        current_herbivores++;
    }

    public void incrementPredatorsCount() {
        current_predators++;
    }

    public void incrementEatCount() {
        current_eat++;
    }

    public void incrementObstaclesCount() {
        current_obstacles++;
    }
}
