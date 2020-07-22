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
    private Districts districts;

    @OneToOne
    private Properties properties;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Districts getDistricts() {
        return districts;
    }

    public void setDistricts(Districts districts)

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
