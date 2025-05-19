package mate.academy.hwspringbootintro.service;

import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.auth.RegisterUserRequestDto;
import mate.academy.hwspringbootintro.dto.auth.UserResponseDto;
import mate.academy.hwspringbootintro.exception.RegistrationException;
import mate.academy.hwspringbootintro.mapper.UserMapper;
import mate.academy.hwspringbootintro.model.ShoppingCart;
import mate.academy.hwspringbootintro.model.User;
import mate.academy.hwspringbootintro.repository.auth.UserRepository;
import mate.academy.hwspringbootintro.repository.shoppingcart.ShoppingCartRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    @Transactional
    public UserResponseDto register(RegisterUserRequestDto registerUserRequestDto)
            throws RegistrationException {
        registerUserRequestDto.setPassword(
                passwordEncoder.encode(registerUserRequestDto.getPassword()));
        if (userRepository.existsByEmail(registerUserRequestDto.getEmail())) {
            throw new RegistrationException("Email is already in use. Please try another one.");
        }
        User newUser = userRepository.save(userMapper.toEntity(registerUserRequestDto));
        shoppingCartRepository.save(new ShoppingCart(newUser));
        return userMapper.toDto(newUser);
    }
}
