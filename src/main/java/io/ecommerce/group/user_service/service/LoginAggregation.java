package io.ecommerce.group.user_service.service;

import io.ecommerce.group.user_service.domain.dto.response.LoginResponse;
import io.ecommerce.group.user_service.domain.dto.response.TokenResponse;
import io.ecommerce.group.user_service.domain.entity.SystemUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginAggregation {

    private final SystemUserService systemUserService;
    private final AuthService authService;
    private final TokenService tokenService;

    public LoginResponse login(String email, String password, boolean stayConnected) {
        SystemUser user = systemUserService.findById(email);
        authService.login(user, password);

        TokenResponse newAccessToken = tokenService.generateAccessToken(user, stayConnected);
        TokenResponse newRefreshToken = tokenService.generateRefreshToken(user, stayConnected);

        return new LoginResponse(newAccessToken.token(), newAccessToken.expireIn(), newRefreshToken.token(), newRefreshToken.expireIn());
    }
}
