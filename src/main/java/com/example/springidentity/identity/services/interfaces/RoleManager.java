package com.example.springidentity.identity.services.interfaces;

import com.example.springidentity.identity.entities.IdentityRole;

public interface RoleManager<T extends IdentityRole<?>> {
    void createRole(T role);

    T getRoleById(Object id);
}
