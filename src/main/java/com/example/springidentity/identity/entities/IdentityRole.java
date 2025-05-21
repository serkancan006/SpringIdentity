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
public abstract class IdentityRole<PK> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PK id;
    @Column(unique=true, nullable=false)
    private String roleName;


    @ManyToMany(mappedBy = "roles")
    private Set<IdentityUser<PK>> users;
}
