package com.example.SpringIdentityLib.identity.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class IdentityUser<PK> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected PK id;
    @Column(unique=true, nullable=false)
    protected String username;
    @Column(nullable=false)
    protected String passwordHash;
    @Column(unique=true)
    protected String email;
    @Column
    protected boolean emailConfirmed;
    @Column
    protected boolean enabled;
    @Column(nullable = false)
    protected boolean lockoutEnabled;              // Lockout mekanizması aktif mi?
    @Column(nullable = false)
    protected int failedLoginAttempts = 0;         // Başarısız giriş denemesi sayısı


    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    protected Set<IdentityRole<PK>> roles;
}
