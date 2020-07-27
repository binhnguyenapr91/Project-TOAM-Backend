package edu.codegym.toam.repository;

import edu.codegym.toam.model.ERole;
import edu.codegym.toam.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
    Optional<Role> findByName(ERole name);
}
