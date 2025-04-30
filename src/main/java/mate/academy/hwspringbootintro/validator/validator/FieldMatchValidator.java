package mate.academy.hwspringbootintro.validator.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import mate.academy.hwspringbootintro.exception.RegistrationException;
import mate.academy.hwspringbootintro.validator.annotation.FieldMatch;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        Class<?> clazz = obj.getClass();
        Field firstField;
        Field secondField;
        Object firstFieldValue;
        Object secondFieldValue;
        try {
            firstField = clazz.getDeclaredField(firstFieldName);
            firstField.setAccessible(true);
            firstFieldValue = firstField.get(obj);
            secondField = clazz.getDeclaredField(secondFieldName);
            secondField.setAccessible(true);
            secondFieldValue = secondField.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (!((firstFieldValue != null && secondFieldValue != null)
                && (firstFieldValue.equals(secondFieldValue)))) {
            throw new RegistrationException(message);
        }
        return true;
    }
}
