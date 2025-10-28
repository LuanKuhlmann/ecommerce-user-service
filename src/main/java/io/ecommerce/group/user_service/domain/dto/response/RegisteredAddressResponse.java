package io.ecommerce.group.user_service.domain.dto.response;

import java.time.Instant;

public record RegisteredAddressResponse(
        Long id,
        Instant createdAt
) {
}
