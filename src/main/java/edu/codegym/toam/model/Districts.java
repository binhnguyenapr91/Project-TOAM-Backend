package edu.codegym.toam.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Districts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private Cities cities;
}
