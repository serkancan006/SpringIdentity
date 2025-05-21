package com.example.SpringIdentityTest.repositories;


import com.example.SpringIdentityLib.identity.repositories.IdentityRoleRepo;
import com.example.SpringIdentityTest.entities.AppRole;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepo extends IdentityRoleRepo<AppRole, Long> {
}
