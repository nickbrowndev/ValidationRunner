package validation.test;

import validation.ValidationResult;
import validation.Validator;
import validation.annotation.Validation;

import java.util.Map;

import static validation.ValidationRunnerTest.ALWAYS_INVALID;

@Validation(name=ALWAYS_INVALID)
public class AlwaysInvalidValidator implements Validator {

    @Override
    public ValidationResult validate(Map<String, String> values) {
        return ValidationResult.invalid("Always invalid");
    }
}
