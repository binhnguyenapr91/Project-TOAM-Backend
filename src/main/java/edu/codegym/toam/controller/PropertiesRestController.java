package edu.codegym.toam.controller;

import edu.codegym.toam.model.Comments;
import edu.codegym.toam.model.Properties;
import edu.codegym.toam.service.comments.ICommentsService;
import edu.codegym.toam.service.contract.IContractService;
import edu.codegym.toam.service.properties.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/property")
public class PropertiesRestController {

    @Autowired
    IPropertiesService propertiesService;

    @Autowired
    IContractService contractService;

    @Autowired
    ICommentsService commentsService;

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

    @PostMapping("")
    public ResponseEntity<Properties> createProperties(@RequestBody Properties properties) {
        try {
            return ResponseEntity.ok(this.propertiesService.create(properties));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping()
    public ResponseEntity<Properties> updateProperties(@RequestBody Properties properties) {

        try {
            return ResponseEntity.ok(this.propertiesService.update(properties));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePropertyById(@PathVariable Long id) {
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
        return ResponseEntity.ok(this.propertiesService.findAllPropertiesByHostId(id));
    }

    //    Tìm properties theo quận or thành phố or tên properties or địa chỉ
    @GetMapping("/filter/{key}")
    public ResponseEntity<Iterable<Properties>> searchForProperties(@PathVariable String key) {
        return ResponseEntity.ok(this.propertiesService.filterProperties(key));
    }

    //    Tìm properties theo (quận or thành phố or tên properties or địa chỉ) và
//    @GetMapping("/filter/{key}")
//    public ResponseEntity<Iterable<Properties>> searchForProperties(@PathVariable String key) {
//        return ResponseEntity.ok(this.propertiesService.filterProperties(key));
//    }

    //    Phân loại nhà
    @GetMapping("/properties/propertyType/{propertyTypeId}")
    public ResponseEntity<Iterable<Properties>> searchForProperties(@PathVariable Long propertyTypeId) {
        return ResponseEntity.ok(this.propertiesService.findPropertiesByType(propertyTypeId));
    }

    //    Lấy tất cả comments của 1 property
    @GetMapping("/{propertyId}/comments")
    public ResponseEntity<Iterable<Comments>> getPropertyComments(@PathVariable Long propertyId) {
        return ResponseEntity.ok(this.commentsService.findAllCommentByPropertyId(propertyId));
    }

}
