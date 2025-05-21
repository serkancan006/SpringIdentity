package com.example.SpringIdentityTest.repositories;


import com.example.SpringIdentityLib.identity.repositories.IdentityUserRepo;
import com.example.SpringIdentityTest.entities.AppUser;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends IdentityUserRepo<AppUser, Long> {
}
