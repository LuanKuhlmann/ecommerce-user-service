package io.ecommerce.group.user_service.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.action.internal.OrphanRemovalAction;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "system_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String cpf;

    private String password;

    @Column(unique = true, nullable = false)
    private String mail;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDate birthday;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "systemUser")
    private List<Address> addresses = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "system_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    public SystemUser(String name, String cpf, String password, String mail, String phone, LocalDate birthday) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.mail = mail;
        this.phone = phone;
        this.birthday = birthday;
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

    public boolean loginAuthentication(final String password, final PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }
}
