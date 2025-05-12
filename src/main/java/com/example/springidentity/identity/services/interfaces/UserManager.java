package com.example.springidentity.identity.services.interfaces;

import com.example.springidentity.identity.entities.IdentityUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserManager<T extends IdentityUser<?>> {

    // CRUD
    void createUser(T user);
    void updateUser(T user);
    void deleteUser(Object id);
    T getUserById(Object id);
    T getUserByUsername(String userName);
    T getUserByEmail(String email);
    List<T> getUsers();

    // Role yönetimi
    @Transactional
    void assignRole(String username, String roleName);
    @Transactional
    void removeRole(String username, String roleName);
    @Transactional
    void clearRoles(String username);
    boolean hasRole(String username, String roleName);

    // Existence kontrolleri
    boolean hasUser(Object userId);
    boolean hasUserByEmail(String email);
    boolean hasUserByUserName(String userName);

    // Şifre & güvenlik
    @Transactional
    void changePassword(String username, String newPassword);
    boolean checkPasswordSignIn(String username, String rawPassword);

    // Durum yönetimi
    @Transactional
    void lockUser(String username);
    @Transactional
    void unlockUser(String username);
    @Transactional
    void confirmEmail(String username);
    @Transactional
    void unconfirmEmail(String username);

    // Arama / filtreleme
    List<T> findUsersByEmailContains(String emailSubstring);
    List<T> findUsersByUsernameContains(String usernameSubstring);
    List<T> findUsersByStatus(boolean isEnabled, boolean isEmailConfirmed);

    // Batch işlemler
    @Transactional
    void createUsers(List<T> users);
    @Transactional
    void assignRoles(String username, List<String> roleNames);

    // Sayaç
    long getUserCount();
}
