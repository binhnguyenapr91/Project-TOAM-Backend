package edu.codegym.toam.repository;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.Properties;
import edu.codegym.toam.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByUsername(String username);

    Iterable<Account> findAccountByRoles(Role role);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
