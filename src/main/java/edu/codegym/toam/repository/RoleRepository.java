package edu.codegym.toam.repository;

import edu.codegym.toam.model.ERole;
import edu.codegym.toam.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
@CrossOrigin(origins = "*")
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
    Optional<Role> findByName(ERole name);
}
