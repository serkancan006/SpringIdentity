package com.example.springidentity.identity.services;

import com.example.springidentity.identity.entities.IdentityRole;
import com.example.springidentity.identity.exceptions.RoleNotFoundException;
import com.example.springidentity.identity.repositories.IdentityRoleRepo;
import com.example.springidentity.identity.services.interfaces.RoleManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleManagerImpl<T extends IdentityRole<Object>> implements RoleManager<T> {
    private final IdentityRoleRepo<T, Object> identityRoleRepo;

    @Override
    public void createRole(T role) {
        identityRoleRepo.save(role);
    }

    @Override
    public void updateRole(T role) {
        identityRoleRepo.save(role);
    }

    @Override
    public void deleteRole(Object roleId){
        identityRoleRepo.deleteById(roleId);
    }

    @Override
    public T getRoleById(Object roleId) {
        return identityRoleRepo.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Rol bulunamadı: " + roleId));
    }

    @Override
    public T getRoleByRoleName(String roleName) {
        return identityRoleRepo.findByRoleName(roleName).orElseThrow(() -> new RoleNotFoundException("Rol bulunamadı: " + roleName));
    }

    @Override
    public List<T> getRoles() {
        return identityRoleRepo.findAll();
    }

    @Override
    public void createRoles(List<T> roles) {
        identityRoleRepo.saveAll(roles);
    }

    @Override
    public void deleteRoles(List<Object> roleIds) {
        for (Object roleId : roleIds) {
            identityRoleRepo.deleteById(roleId);
        }
    }



}
