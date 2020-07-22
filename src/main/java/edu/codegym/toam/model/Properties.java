package edu.codegym.toam.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float size;
    private int bedrooms;
    private int bathrooms;
    private float price;
    private String description;
    private String images;
    private String videos;
    private boolean status;
    private String notAvailableTime;

    @ManyToOne
    @JoinColumn(name = "propertyTypeId")
    private PropertiesTypes propertiesTypes;

    @OneToOne
    @JoinColumn(name = "addressId")
    private Addresses addresses;

    @ManyToOne
    @JoinColumn(name = "hostId")
    private Account hostId;

    @OneToMany
    @JoinColumn(name = "renterId")
    private List<Account> renterId;
}
