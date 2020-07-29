package edu.codegym.toam.controller;

import edu.codegym.toam.model.Districts;
import edu.codegym.toam.service.district.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    Lấy các tỉnh/quận bằng cityId
    @GetMapping("/{cityId}")
    public ResponseEntity<Iterable<Districts>> getAllDistrictByCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(this.districtService.getAllDistrictByCity(cityId));
    }
}
