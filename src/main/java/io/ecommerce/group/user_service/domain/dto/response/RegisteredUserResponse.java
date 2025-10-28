package io.ecommerce.group.user_service.domain.dto.response;

import java.time.Instant;

public record RegisteredUserResponse(
        Long id,
        String name,
        String cpf,
        RegisteredAddressResponse registeredAddressResponse,
        Instant createdAt
) {
}
