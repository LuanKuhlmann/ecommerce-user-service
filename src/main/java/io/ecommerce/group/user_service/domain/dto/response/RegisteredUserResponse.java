package io.ecommerce.group.user_service.domain.dto.response;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public record RegisteredUserResponse(
        String id,
        String name,
        String cpf,
        String mail,
        LocalDate birthDay,
        List<RegisteredAddressResponse> addresses,
        Instant createdAt
) {
}
