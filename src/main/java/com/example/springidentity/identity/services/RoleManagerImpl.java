package com.example.springidentity.identity.services;

import com.example.springidentity.identity.entities.IdentityRole;
import com.example.springidentity.identity.repositories.IdentityRoleRepo;
import com.example.springidentity.identity.services.interfaces.RoleManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleManagerImpl<T extends IdentityRole<Object>> implements RoleManager<T> {
    private final IdentityRoleRepo<T, Object> identityRoleRepo;

    @Override
    public void createRole(T role) {
        identityRoleRepo.save(role);
    }

    @Override
    public T getRoleById(Object id) {
        return identityRoleRepo.findById(id).orElseThrow();
    }


}
