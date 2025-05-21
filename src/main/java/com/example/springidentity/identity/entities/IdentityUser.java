package com.example.springidentity.identity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class IdentityUser<PK> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PK id;
    @Column(unique=true, nullable=false)
    private String username;
    @Column(nullable=false)
    private String passwordHash;
    @Column(unique=true)
    private String email;
    @Column
    private boolean emailConfirmed;
    @Column
    private boolean enabled;
    @Column(nullable = false)
    private boolean lockoutEnabled;              // Lockout mekanizması aktif mi?
    @Column(nullable = false)
    private int failedLoginAttempts = 0;         // Başarısız giriş denemesi sayısı


    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<IdentityRole<PK>> roles;
}
