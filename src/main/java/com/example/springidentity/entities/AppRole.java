package com.example.springidentity.entities;

import com.example.springidentity.identity.entities.IdentityRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppRole extends IdentityRole<Long> {
    @Column
    private String testProp;
}
