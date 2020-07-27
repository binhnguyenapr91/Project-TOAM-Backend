package edu.codegym.toam.controller;

import edu.codegym.toam.model.Addresses;
import edu.codegym.toam.service.adrress.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "*")
public class AddressRestController {
    @Autowired
     IAddressService addressService;

    @GetMapping("")
    public ResponseEntity<Iterable<Addresses>> getAccounts() {
        return ResponseEntity.ok(this.addressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Addresses> getAccountById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.addressService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Addresses> createAccount(@RequestBody Addresses addresses) {
        try {
            return ResponseEntity.ok(this.addressService.create(addresses));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping()
    public ResponseEntity<Addresses> updateAccount(@RequestBody Addresses addresses) {

        try {
            return ResponseEntity.ok(this.addressService.update(addresses));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeBlogById(@PathVariable Long id) {
        try {
            this.addressService.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
