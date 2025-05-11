package com.example.springidentity.identity.repositories;

import com.example.springidentity.identity.entities.IdentityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdentityUserRepo<Table extends IdentityUser<PK>, PK> extends JpaRepository<Table, PK> {
    // Kullanıcıyı username ile bulma  uniq oldukları için tek döner
    Optional<Table> findByUsername(String username);

    // Kullanıcıyı email ile bulma uniq oldukları için tek döner
    Optional<Table> findByEmail(String email);
}
