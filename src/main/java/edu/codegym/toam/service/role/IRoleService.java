package edu.codegym.toam.service.role;

import edu.codegym.toam.model.Role;

import java.util.Optional;

public interface IRoleService {
    Role findByName(String name);

    Iterable<Role> findAll();

    Optional<Role> findById(Long id);
}
