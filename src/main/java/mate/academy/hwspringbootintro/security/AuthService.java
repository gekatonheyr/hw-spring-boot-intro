package mate.academy.hwspringbootintro.security;

import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.auth.UserLoginRequestDto;
import mate.academy.hwspringbootintro.dto.auth.UserLoginResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;

    public UserLoginResponseDto authenticate(UserLoginRequestDto userLoginRequestDto) {
        final Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequestDto.email(),
                        userLoginRequestDto.password()
                )
        );
        String token = jwtUtils.generateToken(authentication.getName());
        return new UserLoginResponseDto(token);
    }
}
