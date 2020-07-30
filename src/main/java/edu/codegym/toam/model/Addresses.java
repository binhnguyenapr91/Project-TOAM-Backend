package edu.codegym.toam.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"street", "districts_id"})
})
@Entity
@Data

public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 10,max = 100)
    private String street;

    @ManyToOne
    @JoinColumn(name = "districts_id")
    private Districts districts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
