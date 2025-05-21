package com.example.SpringIdentityTest.controllers;

import com.example.SpringIdentityTest.entities.AppUser;
import com.example.SpringIdentityTest.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Test")
@RequiredArgsConstructor
public class TestController {
    private final AppUserService appUserService;

    @GetMapping("/get")
    public String testEndpoint(){
        AppUser appUser = AppUser.builder().firstName("Serkan").Lastname("Kaya").username("serkancan").passwordHash("123456Aa*").email("serkan@gmail.com").build();

        appUserService.createUser(appUser);
        return "kullanıcı kaydı başarılıdr";
    }
}
