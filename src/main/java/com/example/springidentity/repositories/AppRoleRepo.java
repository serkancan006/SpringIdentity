package com.example.springidentity.repositories;

import com.example.springidentity.entities.AppRole;
import com.example.springidentity.identity.repositories.IdentityRoleRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepo extends IdentityRoleRepo<AppRole, Long> {
}
