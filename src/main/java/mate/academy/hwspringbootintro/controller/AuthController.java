package mate.academy.hwspringbootintro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.auth.RegisterUserRequestDto;
import mate.academy.hwspringbootintro.dto.auth.UserResponseDto;
import mate.academy.hwspringbootintro.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication part of API", description = "This part is responsible for users"
        + "authentication purposes inside the project.")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @Operation(summary = "User registration endpoint", description = "This endpoint accepts "
            + "registration data of user and includes corresponding information to database. "
            + "Unauthorized users will not bee allowed to purchase products or see most "
            + "significant events.")
    @PostMapping("/register")
    public UserResponseDto register(
            @RequestBody @Valid RegisterUserRequestDto registerUserRequestDto
    ) {
        return userService.register(registerUserRequestDto);
    }
}
