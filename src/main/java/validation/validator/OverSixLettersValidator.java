package validation.validator;

import validation.ValidationResult;
import validation.Validator;
import validation.annotation.Validation;

import java.util.Map;
import java.util.Optional;

@Validation(name="Greater6Letters")
public class OverSixLettersValidator implements Validator {
    @Override
    public ValidationResult validate(Map<String, String> values) {
        ValidationResult result = ValidationResult.invalid("Value is less than 6 letters");
        Optional<String> valueOptional = Optional.ofNullable(values.get("text"));
        if (valueOptional.isPresent()) {
            String value = valueOptional.get();
            if (value.length() >= 6) {
                result = ValidationResult.valid();
            }
        }

        return result;
    }
}
