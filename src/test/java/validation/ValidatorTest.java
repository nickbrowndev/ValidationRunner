package validation;

import org.junit.jupiter.api.Test;
import validation.test.AlwaysInvalidValidator;
import validation.test.AlwaysValidValidator;

import static org.junit.jupiter.api.Assertions.*;
import static validation.ValidationRunnerTest.ALWAYS_INVALID;
import static validation.ValidationRunnerTest.ALWAYS_VALID;

class ValidatorTest {

    @Test
    public void shouldReturnInvalidAnnotation() {
        Validator validator = new AlwaysInvalidValidator();
        assertTrue(validator.getAnnotation(ALWAYS_INVALID).isPresent());
        assertFalse(validator.getAnnotation(ALWAYS_VALID).isPresent());
    }

    @Test
    public void shouldReturnValidAnnotation() {
        Validator validator = new AlwaysValidValidator();
        assertTrue(validator.getAnnotation(ALWAYS_VALID).isPresent());
        assertFalse(validator.getAnnotation(ALWAYS_INVALID).isPresent());
    }

}