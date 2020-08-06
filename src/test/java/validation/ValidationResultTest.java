package validation;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationResultTest {

    @Test
    public void shouldCreateValidResult() {
        ValidationResult result = ValidationResult.valid();
        assertTrue(result.isValid());
        assertTrue(result.getMessage().isEmpty());
    }

    @Test
    public void shouldCreateInvalidResultAndSetMessage() {
        final String message = "Validation message here";
        ValidationResult result = ValidationResult.invalid(message);
        assertFalse(result.isValid());
        assertFalse(result.getMessage().isEmpty());
        assertEquals(message, result.getMessage().get());
    }
}