package com.example.SpringIdentityLib.identity.services;


import com.example.SpringIdentityLib.identity.entities.IdentityRole;
import com.example.SpringIdentityLib.identity.exceptions.RoleNotFoundException;
import com.example.SpringIdentityLib.identity.repositories.IdentityRoleRepo;
import com.example.SpringIdentityLib.identity.services.interfaces.RoleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
