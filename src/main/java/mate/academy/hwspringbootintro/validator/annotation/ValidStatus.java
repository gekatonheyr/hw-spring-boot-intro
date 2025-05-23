package mate.academy.hwspringbootintro.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import mate.academy.hwspringbootintro.validator.validator.StatusValidator;

@Documented
@Constraint(validatedBy = StatusValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStatus {
    String message() default "Invalid status provided. RTFM";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
