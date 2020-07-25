package edu.codegym.toam.controller;

import com.sipios.springsearch.anotation.SearchSpec;
import edu.codegym.toam.model.Properties;
import edu.codegym.toam.repository.PropertiesRepository;
import edu.codegym.toam.service.properties.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/property")
public class PropertiesRestController {

    @Autowired
    IPropertiesService propertiesService;

    @GetMapping
    public ResponseEntity<Iterable<Properties>> getProperties() {
        return ResponseEntity.ok(this.propertiesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Properties> getPropertiesById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.propertiesService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Properties> createProperties(@RequestBody Properties properties) {

        try {
            return ResponseEntity.ok(this.propertiesService.create(properties));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping()
    public ResponseEntity<Properties> updateAccount(@RequestBody Properties properties) {

        try {
            return ResponseEntity.ok(this.propertiesService.update(properties));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeBlogById(@PathVariable Long id) {
        try {
            this.propertiesService.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    Tìm Properties theo host id
    @GetMapping("/host/{id}")
    public ResponseEntity<Iterable<Properties>> getHostProperties(@PathVariable Long id) {
        return ResponseEntity.ok(this.propertiesService.findAllPropertiesById(id));
    }

    @Autowired
    PropertiesRepository propertiesRepository;

//    public void CarController(PropertiesRepository propertiesRepository) {
//        this.propertiesRepository = propertiesRepository;
//    }

//    Cú pháp search, bên front end cần tạo ra một đường dẫn tương tự để search
//    http://localhost:8080/api/property/filter?search=addresses.districts.cities.name:'Hồ Chí Minh'
//    http://localhost:8080/api/property/filter?search=addresses.districts.name:'Thủ Đức'
//    http://localhost:8080/api/property/filter?search=addresses.districts.cities.name:'Thủ Đức' OR addresses.districts.name:'Thủ Đức'

//    localhost:8080/api/property/filter?search=(addresses.districts.cities.name:'Hồ Chí Minh'
//     OR addresses.districts.name:'Thủ Đức'
//     OR name:'green')
//     AND bedrooms:1
//     AND bathrooms:1

//*các trường phải có giá trị mặc định không sẽ bị lỗi

    @GetMapping("/filter")
    public ResponseEntity<List<Properties>> searchForProperties(@SearchSpec Specification<Properties> specs) {
        return new ResponseEntity<>(propertiesRepository.findAll(Specification.where(specs)), HttpStatus.OK);
    }

    @GetMapping("/filter/{key}")
    public ResponseEntity<Iterable<Properties>> searchForProperties(@PathVariable String key) {
        return ResponseEntity.ok(this.propertiesRepository.filterProperties(key));
    }

}
