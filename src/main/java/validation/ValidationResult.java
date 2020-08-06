package validation;

import java.util.List;
import java.util.Optional;

public class ValidationResult {

    private final boolean isValid;
    private final String message;

    private ValidationResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public static ValidationResult invalid(String message) {
        return new ValidationResult(false, message);
    }

    public static ValidationResult valid() {
        return new ValidationResult(true, null);
    }

    public boolean isValid() {
        return isValid;
    }

    public Optional<String> getMessage() {
        return Optional.ofNullable(this.message);
    }
}
