package com.example.springidentity.identity.services.interfaces;

import com.example.springidentity.identity.entities.IdentityUser;

import java.util.List;

public interface UserManager<T extends IdentityUser<?>> {
    void saveUser(T user);

    void deleteUser(Object id);

    T getUserById(Object id);

    T getUserByUsername(String userName);

    T getUserByEmail(String email);

    List<T> getUsers();

    void changeEmailConfirm(T user, boolean emalConfirmStatus);

    void changeEmailConfirm(Object id, boolean emalConfirmStatus);
}
