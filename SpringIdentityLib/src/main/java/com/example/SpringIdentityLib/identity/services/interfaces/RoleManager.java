package com.example.SpringIdentityLib.identity.services.interfaces;


import com.example.SpringIdentityLib.identity.entities.IdentityRole;

import java.util.List;

public interface RoleManager<T extends IdentityRole<?>> {
    void createRole(T role);

    void updateRole(T role);

    void deleteRole(Object roleId);

    T getRoleById(Object id);

    T getRoleByRoleName(String roleName);

    List<T> getRoles();

    void createRoles(List<T> roles);

    void deleteRoles(List<Object> roleIds);
}
