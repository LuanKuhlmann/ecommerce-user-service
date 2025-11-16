package io.ecommerce.group.user_service.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user_addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "system_user_id")
    private SystemUser systemUser;

    private String street;
    private String number;
    private String district;
    private String city;
    private String state;
    private String addressType;
    private Boolean principal;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    public Address(String street, String number, String district, String city, String state, String addressType, Boolean principal) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
        this.addressType = addressType;
        this.principal = principal;
    }

    @PrePersist
    private void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PostUpdate
    private void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
