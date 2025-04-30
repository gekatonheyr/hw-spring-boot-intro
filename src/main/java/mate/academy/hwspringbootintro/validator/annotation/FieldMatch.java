package mate.academy.hwspringbootintro.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import mate.academy.hwspringbootintro.validator.validator.FieldMatchValidator;



/**
 * Validation annotation to validate that 2 fields have the same value.
 * An array of fields and their matching confirmation fields can be supplied.
 * <p>
 * Example, compare 1 pair of fields:
 * @FieldMatch
 * (first = "password", second = "confirmPassword", message = "The password fields must match"),
 * <p>
 * Example, compare more than 1 pair of fields:
 * @FieldMatch.List
 * ({
 *   @FieldMatch
 *   (first = "password", second = "confirmPassword" message = "The password fields must match"),
 *   @FieldMatch
 *   (first = "email", second = "confirmEmail", message = "The email fields must match")
 * })
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {
    String message() default "{constraints.fieldmatch}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return The first field
     */
    String first();

    /**
     * @return The second field
     */
    String second();

    /**
     * Defines several <code>@FieldMatch</code> annotations on the same element
     *
     * @see FieldMatch
     */
    @Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        FieldMatch[] value();
    }
}
