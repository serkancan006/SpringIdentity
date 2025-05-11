package com.example.springidentity.identity.services;

import com.example.springidentity.identity.entities.IdentityUser;
import com.example.springidentity.identity.repositories.IdentityUserRepo;
import com.example.springidentity.identity.services.interfaces.UserManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserManagerImpl<T extends IdentityUser<Object>> implements UserManager<T> {
    private final IdentityUserRepo<T, Object> identityUserRepo;

    @Override
    public void saveUser(T user) {
        identityUserRepo.save(user);
    }

    @Override
    public void deleteUser(Object id){
        identityUserRepo.deleteById(id);
    }

    @Override
    public T getUserById(Object userId) {
        return identityUserRepo.findById(userId).orElseThrow();
    }

    @Override
    public T getUserByUsername(String userName){
        return identityUserRepo.findByUsername(userName).orElseThrow();
    }

    @Override
    public T getUserByEmail(String email){
        return identityUserRepo.findByEmail(email).orElseThrow();
    }

    @Override
    public List<T> getUsers(){
        return identityUserRepo.findAll();
    }

    @Override
    public void changeEmailConfirm(T user, boolean emalConfirmStatus){
        user.setEmailConfirmed(emalConfirmStatus);
        identityUserRepo.save(user);
    }

    @Override
    public void changeEmailConfirm(Object userId, boolean emalConfirmStatus){
        var user = identityUserRepo.findById(userId).orElseThrow();
        user.setEmailConfirmed(emalConfirmStatus);
        identityUserRepo.save(user);
    }





}
