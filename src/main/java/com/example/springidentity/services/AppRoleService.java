package com.example.springidentity.services;

import com.example.springidentity.entities.AppRole;
import com.example.springidentity.identity.services.interfaces.RoleManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppRoleService {
    private final RoleManager<AppRole> roleRoleManager;

    public void createRole(AppRole appRole){
        roleRoleManager.createRole(appRole);
    }
}
