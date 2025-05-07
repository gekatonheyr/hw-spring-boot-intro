package mate.academy.hwspringbootintro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.auth.RegisterUserRequestDto;
import mate.academy.hwspringbootintro.dto.auth.UserLoginRequestDto;
import mate.academy.hwspringbootintro.dto.auth.UserLoginResponseDto;
import mate.academy.hwspringbootintro.dto.auth.UserResponseDto;
import mate.academy.hwspringbootintro.security.AuthService;
import mate.academy.hwspringbootintro.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication part of API", description = "This part is responsible for users"
        + "authentication purposes inside the project.")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @Operation(summary = "User registration endpoint", description = "This endpoint accepts "
            + "registration data of user and includes corresponding information to database. "
            + "Unauthorized users will not bee allowed to purchase products or see most "
            + "significant events.")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(
            @RequestBody @Valid RegisterUserRequestDto registerUserRequestDto
    ) {
        return userService.register(registerUserRequestDto);
    }

    @Operation(summary = "Endpoint for user login", description = "Use this endpoint to "
            + "authenticate user by email and password given during registration. Use JSON to "
            + "send data.")
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto
                                                  userLoginRequestDto) {
        return authService.authenticate(userLoginRequestDto);
    }
}
