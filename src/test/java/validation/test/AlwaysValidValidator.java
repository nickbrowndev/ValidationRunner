package validation.test;

import validation.ValidationResult;
import validation.Validator;
import validation.annotation.Validation;

import java.util.Map;

import static validation.ValidationRunnerTest.ALWAYS_INVALID;
import static validation.ValidationRunnerTest.ALWAYS_VALID;

@Validation(name=ALWAYS_VALID)
public class AlwaysValidValidator implements Validator {

    @Override
    public ValidationResult validate(Map<String, String> values) {
        return ValidationResult.valid();
    }

}
