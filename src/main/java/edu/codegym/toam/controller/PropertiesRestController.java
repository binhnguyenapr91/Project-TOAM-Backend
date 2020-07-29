package edu.codegym.toam.controller;
import edu.codegym.toam.model.Comments;
import edu.codegym.toam.model.Properties;
import edu.codegym.toam.service.comments.ICommentsService;
import com.sipios.springsearch.anotation.SearchSpec;
import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.Properties;
import edu.codegym.toam.service.account.AccountService;
import edu.codegym.toam.service.account.CustomAccountDetail;
import edu.codegym.toam.service.account.IAccountService;
import edu.codegym.toam.service.contract.IContractService;
import edu.codegym.toam.service.properties.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @Autowired
    IAccountService accountService;


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
        CustomAccountDetail user = (CustomAccountDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        Account account = accountService.findById(user.getId());
        System.out.println(account);
        properties.setHost(account);
        Properties properties1 =   propertiesService.create(properties);
        return new ResponseEntity<>(properties1, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Properties> updateProperties(@RequestBody Properties properties) {
        CustomAccountDetail user = (CustomAccountDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.findById(user.getId());
        properties.setHost(account);
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

    @GetMapping("/filter/{key}")
    public ResponseEntity<Iterable<Properties>> searchForProperties(@PathVariable String key) {
        return ResponseEntity.ok(this.propertiesService.filterProperties(key));
    }


    @GetMapping("/type/{name}")
    public ResponseEntity<Iterable<Properties>> searchPropertyType(@PathVariable String name) {
        return ResponseEntity.ok(this.propertiesService.findAllByPropertiesTypes(name));
    }


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
