package edu.codegym.toam.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Size(min = 3,max = 50)
    @Column(unique = true,nullable = false)
    private String username;

    @Size(min = 5,max = 50)
    @Column(nullable = false)
    private String password;

    @Email
    @Column(unique = true,nullable = false)
    private String email;

    @Column(unique = true,nullable = false)
    private String phone;

    private boolean status;

    @ManyToOne
    Role role;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
