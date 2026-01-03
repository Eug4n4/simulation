package dialog;

import validator.Validator;

import java.util.Scanner;

public class ScannerIntegerConsoleDialog implements Dialog<Integer> {
    private final Scanner scanner = new Scanner(System.in);
    private final String title;
    private final String errorMessage;
    private final Validator<Integer> validator;

    public ScannerIntegerConsoleDialog(String title, String errorMessage, Validator<Integer> validator) {
        this.title = title;
        this.errorMessage = errorMessage;
        this.validator = validator;
    }

    @Override
    public Integer input() {
        int value;
        while (true) {
            System.out.println(title);
            try {
                value = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println(errorMessage);
            }

        }
        validator.validate(value);
        return value;
    }
}
