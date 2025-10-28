package io.ecommerce.group.user_service.repository;

import io.ecommerce.group.user_service.domain.entity.Role;
import io.ecommerce.group.user_service.domain.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
