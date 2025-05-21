package com.example.SpringIdentityTest.entities;

import com.example.SpringIdentityLib.identity.entities.IdentityRole;
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
public class AppRole extends IdentityRole<Long> {
    @Column
    private String testProp;
}
