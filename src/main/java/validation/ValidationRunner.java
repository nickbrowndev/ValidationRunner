package validation;

import org.reflections.Reflections;
import validation.annotation.Validation;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidationRunner {

    private final String rootPackage;
    private static final Class<?> annotation = String.class;
    private final Reflections reflections;
    private final Map<String, Validator> validators = new HashMap<>();

    public ValidationRunner(String rootPackage) {
        this.rootPackage = rootPackage;
        reflections = new Reflections(rootPackage);
        Set<Class<?>> validatorTypes = reflections.getTypesAnnotatedWith(Validation.class);
        if (!validatorTypes.isEmpty()) {
            validatorTypes.stream().forEach(clazz -> {
                try {
                    Validation[] annotations = clazz.getAnnotationsByType(Validation.class);
                    for (Validation annotation : annotations) {
                        final String name = annotation.name();

                        if (validators.containsKey(name)) {
                            throw new RuntimeException("Duplicate Validator with name '" + name + "'.");
                        }

                        final String[] parameters = annotation.parameters();
                        final Validator validator;
                        if (parameters.length > 0) {
                            validator = (Validator) clazz.getDeclaredConstructor(String[].class).newInstance();
                        } else {
                            validator = (Validator) clazz.getDeclaredConstructor().newInstance();
                        }
                        validators.put(name, validator);
                    }
                    clazz.getAnnotationsByType(Validation.class);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new RuntimeException("Unable to create object of type: " + clazz.getCanonicalName(), e);
                }
            });
        }
    }

    public ValidationResult validate(String[] validatorNames, Map<String, String> values) {
        ValidationResult result = ValidationResult.valid();
        for (String validatorName : validatorNames) {
            if (validators.containsKey(validatorName)) {
                Validator validator = validators.get(validatorName);
                result = validator.validate(values);
                if (!result.isValid()) {
                    break;
                }
            } else {
                throw new IllegalArgumentException("Unknown validator '" + validatorName + "'.");
            }
        }
        return result;
    }
}
