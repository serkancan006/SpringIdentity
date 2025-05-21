package com.example.SpringIdentityTest.entities;

import com.example.SpringIdentityLib.identity.entities.IdentityUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Data
public class AppUser extends IdentityUser<Long> {
    @Column
    private String firstName;
    @Column
    private String Lastname;
}
