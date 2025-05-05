package mate.academy.hwspringbootintro.service;

import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.auth.RegisterUserRequestDto;
import mate.academy.hwspringbootintro.dto.auth.UserResponseDto;
import mate.academy.hwspringbootintro.exception.RegistrationException;
import mate.academy.hwspringbootintro.mapper.UserMapper;
import mate.academy.hwspringbootintro.repository.auth.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(RegisterUserRequestDto registerUserRequestDto)
            throws RegistrationException {
        registerUserRequestDto.setPassword(
                passwordEncoder.encode(registerUserRequestDto.getPassword()));
        if (userRepository.existsByEmail(registerUserRequestDto.getEmail())) {
            throw new RegistrationException("Email is already in use. Please try another one.");
        }
        return userMapper.toDto(userRepository.save(userMapper.toEntity(registerUserRequestDto)));
    }
}
