package com.example.springidentity.entities;

import com.example.springidentity.identity.entities.IdentityUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser extends IdentityUser<Long> {
    @Column
    private String firstName;
    @Column
    private String Lastname;
}
