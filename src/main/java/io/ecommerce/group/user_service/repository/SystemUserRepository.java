package io.ecommerce.group.user_service.repository;

import io.ecommerce.group.user_service.domain.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
}
