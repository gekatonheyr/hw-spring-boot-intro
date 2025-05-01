package mate.academy.hwspringbootintro.service;

import mate.academy.hwspringbootintro.dto.auth.RegisterUserRequestDto;
import mate.academy.hwspringbootintro.dto.auth.UserResponseDto;
import mate.academy.hwspringbootintro.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(RegisterUserRequestDto registerUserRequestDto)
            throws RegistrationException;
}
