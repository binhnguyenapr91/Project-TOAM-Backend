package edu.codegym.toam.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Min(10)
    private float size;
    @NotNull
    @Min(1)
    private int bedrooms;
    @NotNull
    @Min(1)
    private int bathrooms;
    @NotNull
    @Min(3)
    private float price;
    private String description;
    private String images;
    private String videos;

    @ManyToOne
    @JoinColumn(name = "propertyStatusId")
    private PropertyStatus propertyStatus;

    @ManyToOne
    @JoinColumn(name = "propertyTypeId")
    private PropertiesTypes propertiesTypes;

    @OneToOne
    @JoinColumn(name = "addressId")
    private Addresses addresses;

    @ManyToOne
    @JoinColumn(name = "hostId")
    private Account host;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public PropertiesTypes getPropertiesTypes() {
        return propertiesTypes;
    }

    public void setPropertiesTypes(PropertiesTypes propertiesTypes) {
        this.propertiesTypes = propertiesTypes;
    }

    public Addresses getAddresses() {
        return addresses;
    }

    public void setAddresses(Addresses addresses) {
        this.addresses = addresses;
    }

    public Account getHost() {
        return host;
    }

    public void setHost(Account host) {
        this.host = host;
    }

    public PropertyStatus getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(PropertyStatus propertyStatus) {
        this.propertyStatus = propertyStatus;
    }
}
