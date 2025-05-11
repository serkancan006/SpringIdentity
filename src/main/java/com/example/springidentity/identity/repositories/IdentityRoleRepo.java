package com.example.springidentity.identity.repositories;

import com.example.springidentity.identity.entities.IdentityRole;
import com.example.springidentity.identity.entities.IdentityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdentityRoleRepo<Table extends IdentityRole<PK>, PK > extends JpaRepository<Table, PK> {
    // Rolün adını kullanarak rolü bulma
    Optional<Table> findByRoleName(String roleName);

    // Belirli bir rol adına sahip kullanıcıları bulma
    List<Table> findUsersByRoleName(String roleName);

    // Rol ismi ve kullanıcının username'ine göre kullanıcıyı bulma
    Optional<Table> findUserByUsernameAndRoleName(String username, String roleName);
}
