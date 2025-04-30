package mate.academy.hwspringbootintro.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mate.academy.hwspringbootintro.validator.annotation.FieldMatch;

@Getter
@Setter
@FieldMatch(first = "password", second = "repeatPassword", message = "Given passwords has to "
        + "match each other. Please check the input")
public class RegisterUserRequestDto {
    @NotNull
    private String email;
    @NotNull
    @Size(min = 5, max = 20)
    private String password;
    @NotNull
    @Size(min = 5, max = 20)
    private String repeatPassword;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String shippingAddress;
}
