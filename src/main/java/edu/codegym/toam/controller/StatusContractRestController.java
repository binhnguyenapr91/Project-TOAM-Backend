package edu.codegym.toam.controller;

import edu.codegym.toam.model.ContractStatus;
import edu.codegym.toam.service.statusContract.IStatusContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/statusContract")
public class StatusContractRestController {

    @Autowired
    IStatusContractService statusContractService;

    @GetMapping("")
    public ResponseEntity<Iterable<ContractStatus>> getStatusContract() {
        return ResponseEntity.ok(this.statusContractService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractStatus> getStatusContractById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.statusContractService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<ContractStatus> createStatusContract(@RequestBody ContractStatus properties) {

        try {
            return ResponseEntity.ok(this.statusContractService.create(properties));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping()
    public ResponseEntity<ContractStatus> updateStatusContract(@RequestBody ContractStatus properties) {

        try {
            return ResponseEntity.ok(this.statusContractService.update(properties));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeStatusById(@PathVariable Long id) {
        try {
            this.statusContractService.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
