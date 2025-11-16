package io.ecommerce.group.user_service.mapper;

import io.ecommerce.group.user_service.domain.dto.request.RegisterUserRequest;
import io.ecommerce.group.user_service.domain.dto.response.RegisteredUserResponse;
import io.ecommerce.group.user_service.domain.entity.SystemUser;

public class SystemUserMapper {

    public static SystemUser fromRegisterUserRequest(RegisterUserRequest request, String password) {
        return new SystemUser(
                request.name(),
                request.cpf(),
                password,
                request.mail(),
                request.phone(),
                request.birthDay());
    }

    public static RegisteredUserResponse fromSystemUser(SystemUser systemUser) {
        return new RegisteredUserResponse(
                systemUser.getId().toString(),
                systemUser.getName(),
                systemUser.getCpf(),
                systemUser.getMail(),
                systemUser.getBirthday(),
                AddressMapper.toRegisteredAddressResponseList(systemUser.getAddresses()),
                systemUser.getCreatedAt()
        );
    }
}
