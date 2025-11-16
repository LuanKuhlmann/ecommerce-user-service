package io.ecommerce.group.user_service.domain.dto.response;

public record TokenResponse(
        String token,
        Long expireIn
) {
}
