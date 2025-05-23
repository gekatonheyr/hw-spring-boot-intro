package mate.academy.hwspringbootintro.validator.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import mate.academy.hwspringbootintro.model.Order;
import mate.academy.hwspringbootintro.validator.annotation.ValidStatus;

public class StatusValidator implements ConstraintValidator<ValidStatus, Order.Status> {
    public boolean isValid(Order.Status status, ConstraintValidatorContext context) {
        return Arrays.asList(Order.Status.values()).contains(status);
    }
}
