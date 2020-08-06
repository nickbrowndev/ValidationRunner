package validation;

import validation.annotation.Validation;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public interface Validator {

    default Optional<Validation> getAnnotation(String name) {
        Validation[] annotations = this.getClass().getAnnotationsByType(Validation.class);
        Optional<Validation> result = Optional.empty();
        for (Validation val : annotations) {
            if (val.name().equals(name)) {
                result = Optional.of(val);
            }
        }
        return result;
    }

    ValidationResult validate(Map<String, String> values);
}
