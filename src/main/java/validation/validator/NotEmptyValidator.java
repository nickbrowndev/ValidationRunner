package validation.validator;

import validation.ValidationResult;
import validation.Validator;
import validation.annotation.Validation;

import java.util.Map;
import java.util.Optional;

@Validation(name="NotEmpty")
public class NotEmptyValidator implements Validator {
    @Override
    public ValidationResult validate(Map<String, String> values) {
        ValidationResult result = ValidationResult.invalid("Value is empty or blank");
        Optional<String> valueOptional = Optional.ofNullable(values.get("text"));
        if (valueOptional.isPresent()) {
            String value = valueOptional.get();
            if (!value.isBlank()) {
                result = ValidationResult.valid();
            }
        }

        return result;
    }
}
