package me.kimmingyu.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kimmingyu.springbootdeveloper.config.jwt.TokenProvider;
import me.kimmingyu.springbootdeveloper.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken){
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)){
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.fineById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
