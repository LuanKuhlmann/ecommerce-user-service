package io.ecommerce.group.user_service.service;

import io.ecommerce.group.user_service.domain.dto.request.RegisterUserRequest;
import io.ecommerce.group.user_service.domain.entity.SystemUser;
import io.ecommerce.group.user_service.mapper.SystemUserMapper;
import io.ecommerce.group.user_service.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SystemUserService {

    private final SystemUserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public SystemUser registerNewUser(RegisterUserRequest request) {
        String password = passwordEncoder.encode(request.password());
        SystemUser user = SystemUserMapper.fromRegisterUserRequest(request, password);
        return repository.save(user);
    }

    public SystemUser findById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public SystemUser findByMail(String mail) {
        return repository.findByMail(mail)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }
}
