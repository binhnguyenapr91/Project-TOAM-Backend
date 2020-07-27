package edu.codegym.toam.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/renter")
    @PreAuthorize("hasRole('RENTER') or hasRole('HOST') or hasRole('ADMIN')")
    public String userAccess() {
        return "RENTER Content.";
    }

    @GetMapping("/host")
    @PreAuthorize("hasRole('HOST')")
    public String moderatorAccess() {
        return "HOST Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "ADMIN Board.";
    }
}
