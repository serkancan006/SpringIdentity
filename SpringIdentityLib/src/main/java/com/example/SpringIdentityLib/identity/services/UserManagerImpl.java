package com.example.SpringIdentityLib.identity.services;


import com.example.SpringIdentityLib.identity.config.IdentityConfig;
import com.example.SpringIdentityLib.identity.entities.IdentityUser;
import com.example.SpringIdentityLib.identity.exceptions.*;
import com.example.SpringIdentityLib.identity.repositories.IdentityRoleRepo;
import com.example.SpringIdentityLib.identity.repositories.IdentityUserRepo;
import com.example.SpringIdentityLib.identity.services.interfaces.UserManager;
import com.example.SpringIdentityLib.identity.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManagerImpl<T extends IdentityUser<Object>> implements UserManager<T> {
    private final IdentityUserRepo<T, Object> identityUserRepo;
    private final IdentityRoleRepo<?, Object> identityRoleRepo;
    private final PasswordUtil passwordUtil;
    private final IdentityConfig identityConfig;

    @Override
    public void createUser(T user) {
        if (identityUserRepo.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Kullanıcı adı zaten mevcut: " + user.getUsername());
        }
        if (identityUserRepo.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("E-posta zaten mevcut: " + user.getEmail());
        }

        user.setPasswordHash(passwordUtil.encode(user.getPasswordHash()));
        user.setEnabled(identityConfig.isEnabled());
        user.setEmailConfirmed(identityConfig.isEmailConfirmed());
        user.setLockoutEnabled(identityConfig.isLockoutEnabled());
        user.setFailedLoginAttempts(0);

        identityUserRepo.save(user);
    }

    @Override
    public void updateUser(T user) {
        if (user.getPasswordHash() != null) {
            user.setPasswordHash(passwordUtil.encode(user.getPasswordHash()));
        }
        identityUserRepo.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Object id) {
        var user = identityUserRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + id));
        user.getRoles().clear();
        identityUserRepo.delete(user);
    }

    @Override
    public T getUserById(Object userId) {
        return identityUserRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + userId));
    }

    @Override
    public T getUserByUsername(String userName) {
        return identityUserRepo.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + userName));
    }

    @Override
    public T getUserByEmail(String email) {
        return identityUserRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + email));
    }

    @Override
    public List<T> getUsers() {
        return identityUserRepo.findAll();
    }

    @Transactional
    @Override
    public void assignRole(String username, String roleName) {
        var user = loadUser(username);
        if (!user.isEnabled()) {
            throw new UserLockedException("Kullanıcı kilitli: " + username);
        }
        var role = identityRoleRepo.findByRoleName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Rol bulunamadı: " + roleName));
        if (!user.getRoles().add(role)) {
            throw new InvalidRoleAssignmentException("Kullanıcı zaten bu role sahip: " + roleName);
        }
        identityUserRepo.save(user);
    }

    @Transactional
    @Override
    public void removeRole(String username, String roleName) {
        var user = loadUser(username);
        var role = identityRoleRepo.findByRoleName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Rol bulunamadı: " + roleName));
        if (!user.getRoles().remove(role)) {
            throw new InvalidRoleAssignmentException("Kullanıcı bu role sahip değil: " + roleName);
        }
        identityUserRepo.save(user);
    }

    @Transactional
    @Override
    public void clearRoles(String username) {
        var user = loadUser(username);
        user.getRoles().clear();
        identityUserRepo.save(user);
    }

    @Override
    public boolean hasRole(String username, String roleName) {
        return identityUserRepo.existsByUsernameAndRoleName(username, roleName);
    }

    @Override
    public boolean hasUser(Object userId) {
        return identityUserRepo.existsById(userId);
    }

    @Override
    public boolean hasUserByEmail(String email) {
        return identityUserRepo.existsByEmail(email);
    }

    @Override
    public boolean hasUserByUserName(String userName) {
        return identityUserRepo.existsByUsername(userName);
    }

    @Transactional
    @Override
    public void changePassword(String username, String newPassword) {
        var user = loadUser(username);
        user.setPasswordHash(passwordUtil.encode(newPassword));
        identityUserRepo.save(user);
    }

    @Transactional
    @Override
    public void lockUser(String username) {
        var user = loadUser(username);
        user.setEnabled(false);
        identityUserRepo.save(user);
    }

    @Transactional
    @Override
    public void unlockUser(String username) {
        var user = loadUser(username);
        user.setEnabled(true);
        user.setFailedLoginAttempts(0);
        identityUserRepo.save(user);
    }

    @Transactional
    @Override
    public void confirmEmail(String username) {
        var user = loadUser(username);
        if (user.isEmailConfirmed()) {
            throw new EmailAlreadyConfirmedException("E-posta zaten onaylı: " + username);
        }
        user.setEmailConfirmed(true);
        identityUserRepo.save(user);
    }

    @Transactional
    @Override
    public void unconfirmEmail(String username) {
        var user = loadUser(username);
        if (!user.isEmailConfirmed()) {
            throw new EmailNotConfirmedException("E-posta zaten onaysız: " + username);
        }
        user.setEmailConfirmed(false);
        identityUserRepo.save(user);
    }

    @Override
    public List<T> findUsersByEmailContains(String emailSubstring) {
        var users = identityUserRepo.findByEmailContaining(emailSubstring);
        if (users.isEmpty()) {
            throw new UserNotFoundException("E-posta içeren kullanıcı bulunamadı: " + emailSubstring);
        }
        return users;
    }

    @Override
    public List<T> findUsersByUsernameContains(String usernameSubstring) {
        var users = identityUserRepo.findByUsernameContaining(usernameSubstring);
        if (users.isEmpty()) {
            throw new UserNotFoundException("Kullanıcı adı içeren kullanıcı bulunamadı: " + usernameSubstring);
        }
        return users;
    }

    @Override
    public List<T> findUsersByStatus(boolean isEnabled, boolean isEmailConfirmed) {
        return identityUserRepo.findByEnabledAndEmailConfirmed(isEnabled, isEmailConfirmed);
    }

    @Transactional
    @Override
    public void createUsers(List<T> users) {
        users.forEach(user -> {
            user.setPasswordHash(passwordUtil.encode(user.getPasswordHash()));
            user.setLockoutEnabled(identityConfig.isLockoutEnabled());
            user.setFailedLoginAttempts(0);
        });
        identityUserRepo.saveAll(users);
    }

    @Transactional
    @Override
    public void assignRoles(String username, List<String> roleNames) {
        roleNames.forEach(role -> assignRole(username, role));
    }

    @Transactional
    @Override
    public boolean checkPasswordSignIn(String username, String rawPassword) {
        var user = identityUserRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + username));

        // Lockout devre dışıysa sadece şifre kontrolü
        if (!identityConfig.isLockoutEnabled()) {
            return passwordUtil.matches(rawPassword, user.getPasswordHash());
        }

        if (!user.isEnabled()) {
            throw new UserNotEnabledException("Hesap devre dışı: " + username);
        }
        if (!user.isEmailConfirmed()) {
            throw new EmailNotConfirmedException("E-posta onaylı değil: " + username);
        }

        boolean matches = passwordUtil.matches(rawPassword, user.getPasswordHash());
        if (!matches) {
            int attempts = user.getFailedLoginAttempts() + 1;
            user.setFailedLoginAttempts(attempts);
            if (attempts >= identityConfig.getMaxFailedLoginAttempts()) {
                user.setEnabled(false);
            }
            identityUserRepo.save(user);
            throw new TooManyFailedLoginAttemptsException("Başarısız giriş denemesi: " + attempts);
        }

        user.setFailedLoginAttempts(0);
        identityUserRepo.save(user);
        return true;
    }

    @Override
    public long getUserCount() {
        return identityUserRepo.count();
    }

    // Tekrarlanan kullanıcı yükleme + exception fırlatma
    private T loadUser(String username) {
        return identityUserRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + username));
    }
}
