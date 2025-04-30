package mate.academy.hwspringbootintro.service;

import mate.academy.hwspringbootintro.dto.auth.RegisterUserRequestDto;
import mate.academy.hwspringbootintro.dto.auth.UserResponseDto;

public interface UserService {
    UserResponseDto register(RegisterUserRequestDto registerUserRequestDto);
}
