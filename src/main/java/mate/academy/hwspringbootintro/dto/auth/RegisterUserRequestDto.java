package mate.academy.hwspringbootintro.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mate.academy.hwspringbootintro.validator.annotation.FieldMatch;

@Getter
@Setter
@FieldMatch(first = "password", second = "repeatPassword", message = "Given passwords has to "
        + "match each other. Please check the input")
public class RegisterUserRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 5, max = 80)
    private String password;
    @NotBlank
    @Size(min = 5, max = 80)
    private String repeatPassword;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String shippingAddress;
}
