package edu.codegym.toam.controller;
import edu.codegym.toam.model.PropertyStatus;
import edu.codegym.toam.service.propertyStatus.IPropertyStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin("*")
@RequestMapping("api/propertyStatus")
public class PropertyStatusRestController {
    @Autowired
    private IPropertyStatusService propertyStatusService;
    @GetMapping
    public ResponseEntity<Iterable<PropertyStatus>> getAll() {
        Iterable<PropertyStatus> propertyStatuses = propertyStatusService.getAll();
        return new ResponseEntity<>(propertyStatuses, HttpStatus.OK);
    }
}
