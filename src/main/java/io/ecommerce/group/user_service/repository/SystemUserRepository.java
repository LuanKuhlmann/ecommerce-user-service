package io.ecommerce.group.user_service.repository;

import io.ecommerce.group.user_service.domain.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, UUID> {
    Optional<SystemUser> findByMail(String mail);
}
