package com.example.SpringIdentityTest.services;


import com.example.SpringIdentityLib.identity.services.interfaces.RoleManager;
import com.example.SpringIdentityTest.entities.AppRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppRoleService {
    private final RoleManager<AppRole> roleRoleManager;

    public void createRole(AppRole appRole){
        roleRoleManager.createRole(appRole);
    }
}
