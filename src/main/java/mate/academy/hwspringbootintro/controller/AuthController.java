package mate.academy.hwspringbootintro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.auth.RegisterUserRequestDto;
import mate.academy.hwspringbootintro.dto.auth.UserResponseDto;
import mate.academy.hwspringbootintro.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    UserResponseDto register(@RequestBody @Valid RegisterUserRequestDto registerUserRequestDto) {
        return userService.register(registerUserRequestDto);
    }
}
