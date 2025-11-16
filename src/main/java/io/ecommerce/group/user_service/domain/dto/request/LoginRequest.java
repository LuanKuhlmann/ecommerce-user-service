package io.ecommerce.group.user_service.domain.dto.request;

public record LoginRequest(
        String email,
        String password,
        Boolean stayConnected
) {
}
