package edu.codegym.toam.controller;

import edu.codegym.toam.model.PropertiesTypes;
import edu.codegym.toam.service.propertyType.IPropertyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/propertiesType")
public class PropertyTypeController {
    @Autowired
    private IPropertyTypeService propertyTypeService;

    @GetMapping
    public ResponseEntity<Iterable<PropertiesTypes>> getAllPropertiesType() {
        Iterable<PropertiesTypes> propertiesTypes = this.propertyTypeService.findAll();
        return new ResponseEntity<>(propertiesTypes, HttpStatus.OK);
    }
}
