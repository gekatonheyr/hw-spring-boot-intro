package mate.academy.hwspringbootintro.service;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.auth.RegisterUserRequestDto;
import mate.academy.hwspringbootintro.dto.auth.UserResponseDto;
import mate.academy.hwspringbootintro.exception.RegistrationException;
import mate.academy.hwspringbootintro.mapper.UserMapper;
import mate.academy.hwspringbootintro.model.Role;
import mate.academy.hwspringbootintro.model.User;
import mate.academy.hwspringbootintro.repository.auth.RoleRepository;
import mate.academy.hwspringbootintro.repository.auth.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ShoppingCartService shoppingCartService;

    @Override
    @Transactional
    public UserResponseDto register(RegisterUserRequestDto registerUserRequestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(registerUserRequestDto.getEmail())) {
            throw new RegistrationException("Email is already in use. Please try another one.");
        }
        User user = userMapper.toEntity(registerUserRequestDto);
        user.setPassword(passwordEncoder.encode(registerUserRequestDto.getPassword()));
        user.setRoles(Set.of(roleRepository.findRoleByName(Role.RoleName.USER).orElseThrow(()
                        -> new RegistrationException("There is no such role: "
                + Role.RoleName.USER))));
        userRepository.save(user);
        shoppingCartService.createShoppingCart(user);
        return userMapper.toDto(user);
    }
}
