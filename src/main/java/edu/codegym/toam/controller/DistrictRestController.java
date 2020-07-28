package edu.codegym.toam.controller;

import edu.codegym.toam.model.Districts;
import edu.codegym.toam.service.district.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/district")
public class DistrictRestController {
    @Autowired
    private IDistrictService districtService;

    @GetMapping
    public ResponseEntity<Iterable<Districts>> getAllDistrict() {
        Iterable<Districts> districts = districtService.findAllDistrict();
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }
}
