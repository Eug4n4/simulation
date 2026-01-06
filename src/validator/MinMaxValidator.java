package validator;

public class MinMaxValidator implements Validator<Integer> {
    private final int minimum;
    private final int maximum;
    private final String errorMessage;

    public MinMaxValidator(int minimum, int maximum, String errorMessage) {
        this.maximum = maximum;
        this.minimum = minimum;
        this.errorMessage = errorMessage;
    }

    @Override
    public void validate(Integer value) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
