package edu.codegym.toam.controller;

import edu.codegym.toam.model.Contracts;
import edu.codegym.toam.service.contract.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/contract")
public class ContractRestController {
    @Autowired
    IContractService contractService;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<Iterable<Contracts>> getContracts() {
        return ResponseEntity.ok(this.contractService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<Contracts> getContractById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.contractService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<Contracts> createContract(@RequestBody Contracts properties) {
        try {
            return ResponseEntity.ok(this.contractService.create(properties));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<Contracts> updateContract(@RequestBody Contracts properties) {

        try {
            return ResponseEntity.ok(this.contractService.update(properties));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeContractById(@PathVariable Long id) {
        try {
            this.contractService.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
