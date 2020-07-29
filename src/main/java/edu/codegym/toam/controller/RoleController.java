package edu.codegym.toam.controller;

import edu.codegym.toam.model.Role;
import edu.codegym.toam.service.role.IRoleService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<Iterable<Role>> getAllRole() {
        List<Role> roles = new ArrayList<>();
        Optional<Role> roleHost = roleService.findById((long) 2);
        if (roleHost.isPresent()) {
            Role roleHost1 = roleHost.get();
            roles.add(roleHost1);
        }
        Optional<Role> roleRenter = roleService.findById((long) 4);
        if (roleRenter.isPresent()) {
            Role roleRenter1 = roleRenter.get();
            roles.add(roleRenter1);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Iterable<Role>> findAllRole() {
        Iterable<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
