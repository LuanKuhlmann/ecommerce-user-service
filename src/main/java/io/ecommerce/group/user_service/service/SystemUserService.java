package io.ecommerce.group.user_service.service;

import io.ecommerce.group.user_service.domain.dto.request.RegisterUserRequest;
import io.ecommerce.group.user_service.domain.dto.response.RegisteredUserResponse;
import io.ecommerce.group.user_service.domain.entity.Address;
import io.ecommerce.group.user_service.domain.entity.SystemUser;
import io.ecommerce.group.user_service.mapper.AddressMapper;
import io.ecommerce.group.user_service.mapper.SystemUserMapper;
import io.ecommerce.group.user_service.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemUserService {

    private final SystemUserRepository repository;

    public SystemUser registerNewUser(RegisterUserRequest request) {
        String password = request.password();
        return SystemUserMapper.fromRegisterUserRequest(request, password);
    }
}
