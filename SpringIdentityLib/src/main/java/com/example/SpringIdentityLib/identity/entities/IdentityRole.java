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
public abstract class IdentityRole<PK> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected PK id;
    @Column(unique=true, nullable=false)
    protected String roleName;


    @ManyToMany(mappedBy = "roles")
    protected Set<IdentityUser<PK>> users;
}
