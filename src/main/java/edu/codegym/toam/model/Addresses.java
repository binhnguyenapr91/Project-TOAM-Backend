package edu.codegym.toam.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;

    @ManyToOne
    @JoinColumn(name = "districtId")
    private Districts districts;

}
