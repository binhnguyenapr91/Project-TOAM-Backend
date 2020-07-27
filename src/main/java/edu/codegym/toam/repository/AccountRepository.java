package edu.codegym.toam.repository;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByUsername(String username);
    Iterable<Account> findAccountByRole(Role role);
    boolean existsByUsername (String username);
    boolean existsByEmail(String email);
}
