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
    @JoinColumn(name = "addressId",unique = true)
    private Addresses addresses;

    @ManyToOne
    @JoinColumn(name = "hostId")
    private Account hostId;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNotAvailableTime() {
        return notAvailableTime;
    }

    public void setNotAvailableTime(String notAvailableTime) {
        this.notAvailableTime = notAvailableTime;
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

    public Account getHostId() {
        return hostId;
    }

    public void setHostId(Account hostId) {
        this.hostId = hostId;
    }

}
