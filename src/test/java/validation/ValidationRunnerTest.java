package validation;



import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationRunnerTest {

    public static final String ALWAYS_INVALID = "AlwaysInvalid";
    public static final String ALWAYS_VALID = "AlwaysValid";

    @Test
    @Disabled("Error with current version of Reflections")
    public void shouldReturnValidWhenRunNoTestsInPackage() {
        ValidationRunner runner = new ValidationRunner("validation.test.notpresent");
        ValidationResult result = runner.validate(new String[] {ALWAYS_INVALID}, Collections.emptyMap());
        assertTrue(result.isValid());
    }

    @Test
    public void shouldReturnValidWhenNoTestsSpecified() {
        ValidationRunner runner = new ValidationRunner("validation.test");
        ValidationResult result = runner.validate(new String[] {}, Collections.emptyMap());
        assertTrue(result.isValid());
    }

    @Test
    public void shouldValidateValidTest() {
        ValidationRunner runner = new ValidationRunner("validation.test");
        ValidationResult result = runner.validate(new String[] {ALWAYS_VALID}, Collections.emptyMap());
        assertTrue(result.isValid());
    }

    @Test
    public void shouldNotValidateInvalidTest() {
        ValidationRunner runner = new ValidationRunner("validation.test");
        ValidationResult result = runner.validate(new String[]{ALWAYS_INVALID}, Collections.emptyMap());
        assertFalse(result.isValid());
    }

}