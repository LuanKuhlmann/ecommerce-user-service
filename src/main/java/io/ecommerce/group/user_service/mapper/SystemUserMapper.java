package io.ecommerce.group.user_service.mapper;

import io.ecommerce.group.user_service.domain.dto.request.RegisterUserRequest;
import io.ecommerce.group.user_service.domain.entity.SystemUser;

public class SystemUserMapper {

    public static SystemUser fromRegisterUserRequest(RegisterUserRequest request, String password) {
        return new SystemUser(
                request.cpf(),
                password,
                request.mail(),
                request.phone(),
                request.birthDay());
    }
}
