package com.example.SpringIdentityLib.identity.repositories;

import com.example.SpringIdentityLib.identity.entities.IdentityRole;
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

}
