package edu.codegym.toam.service.role;

import edu.codegym.toam.model.Role;

public interface IRoleService {
    Role findByName(String name);
}
