package io.ecommerce.group.user_service.domain.dto.response;

import java.time.Instant;

public record RegisteredAddressResponse(
        String id,
        String street,
        String number,
        String district,
        String city,
        String state,
        String addressType,
        boolean principal,
        Instant createdAt
) {
}
