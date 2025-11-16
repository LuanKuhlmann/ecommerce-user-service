package io.ecommerce.group.user_service.domain.dto.response;

public record LoginResponse(
        String accessToken,
        Long accessTokenExpiresIn,
        String refreshToken,
        Long refreshExpiresIn
) {
}
