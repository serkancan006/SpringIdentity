package com.example.springidentity.identity.repositories;

import com.example.springidentity.identity.entities.IdentityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdentityUserRepo<Table extends IdentityUser<PK>, PK> extends JpaRepository<Table, PK> {
    // Kullanıcıyı username ile bulma  uniq oldukları için tek döner
    Optional<Table> findByUsername(String username);

    // Kullanıcıyı email ile bulma uniq oldukları için tek döner
    Optional<Table> findByEmail(String email);

    // Kullanıcının belirli bir role sahip olup olmadığını kontrol etme
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM IdentityUser u " +
            "JOIN u.roles r WHERE u.username = :username AND r.roleName = :roleName")
    boolean existsByUsernameAndRoleName(String username, String roleName);

    // Kullanıcı var mı kontrolü (username'e göre)
    boolean existsByUsername(String username);

    // Kullanıcı var mı kontrolü (email'e göre)
    boolean existsByEmail(String email);

    // Email'e göre kullanıcı arama (kısmi eşleşme)
    List<Table> findByEmailContaining(String emailSubstring);

    // Username'e göre kullanıcı arama (kısmi eşleşme)
    List<Table> findByUsernameContaining(String usernameSubstring);

    // Kullanıcıyı 'enabled' ve 'emailConfirmed' durumlarına göre arama
    List<Table> findByEnabledAndEmailConfirmed(boolean isEnabled, boolean isEmailConfirmed);

    long count(); // toplam kullanıcı sayısı
}
