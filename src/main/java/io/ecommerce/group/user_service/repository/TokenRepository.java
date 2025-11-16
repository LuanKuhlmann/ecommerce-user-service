package io.ecommerce.group.user_service.repository;

import io.ecommerce.group.user_service.domain.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
}
