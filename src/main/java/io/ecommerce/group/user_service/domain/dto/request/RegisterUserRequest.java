package io.ecommerce.group.user_service.domain.dto.request;

import io.ecommerce.group.user_service.domain.enums.Roles;

import java.time.LocalDate;

public record RegisterUserRequest(
        String name,
        String cpf,
        String password,
        String mail,
        String phone,
        LocalDate birthDay,
        Roles userType,
        RegisterAddressRequest registerAddressRequest
) {
}
