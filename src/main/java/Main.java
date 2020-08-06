import validation.ValidationResult;
import validation.ValidationRunner;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> values = new HashMap<>();
        values.put("text", "not empty");

        ValidationRunner runner = new ValidationRunner("validation");

        ValidationResult result = runner.validate( new String[] {"NotEmpty"}, values);
        printResult(result);

        ValidationResult result2 = runner.validate( new String[] {"NotEmpty", "Greater6Letters"}, values);
        printResult(result2);

        ValidationResult result3 = runner.validate( new String[] {"NotEmpty", "Greater6Letters", "Less4Letters"}, values);
        printResult(result3);
    }

    public static void printResult(ValidationResult result) {
        System.out.println("Valid: " + result.isValid());
        result.getMessage().ifPresent(message -> System.out.println("Message: " + message));
    }
}
