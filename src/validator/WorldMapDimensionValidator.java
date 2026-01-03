package validator;

public class WorldMapDimensionValidator implements Validator<Integer> {
    private final String errorMessage;

    public WorldMapDimensionValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void validate(Integer value) {
        if (value < 10 || value > 100) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
