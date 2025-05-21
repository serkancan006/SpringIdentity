package com.example.SpringIdentityTest.services;


import com.example.SpringIdentityLib.identity.services.interfaces.UserManager;
import com.example.SpringIdentityTest.entities.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppUserService {
    private final UserManager<AppUser> userManager;

    public void createUser(AppUser appUser){
        userManager.createUser(appUser);
    }
}
