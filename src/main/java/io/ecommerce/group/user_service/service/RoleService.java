package io.ecommerce.group.user_service.service;

import io.ecommerce.group.user_service.domain.entity.Role;
import io.ecommerce.group.user_service.domain.enums.Roles;
import io.ecommerce.group.user_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    public Role designRoleToUser(Roles roles) {
        return roleRepository.findByName(roles)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
