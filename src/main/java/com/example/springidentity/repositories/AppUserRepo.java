package com.example.springidentity.repositories;

import com.example.springidentity.entities.AppUser;
import com.example.springidentity.identity.repositories.IdentityUserRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends IdentityUserRepo<AppUser, Long> {
}
