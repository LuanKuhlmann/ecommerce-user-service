package io.ecommerce.group.user_service.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private boolean repealed;

    @Column(name = "STAY_CONNECTED")
    private boolean stayConnected;

    private UUID userId;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        repealed = false;
    }
}
