package com.example.springidentity.services;

import com.example.springidentity.entities.AppUser;
import com.example.springidentity.identity.services.interfaces.UserManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppUserService {
    private final UserManager<AppUser> userManager;

    public void createUser(AppUser appUser){
        userManager.createUser(appUser);
    }
}
