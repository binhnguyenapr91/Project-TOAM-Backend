package edu.codegym.toam.controller;

import edu.codegym.toam.exception.ContractException;
import edu.codegym.toam.model.Contracts;
import edu.codegym.toam.service.contract.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping("api/contract")

public class ContractRestController {
    @Autowired
    IContractService contractService;

    @GetMapping("")
    public ResponseEntity<Iterable<Contracts>> getContracts() {
        return ResponseEntity.ok(this.contractService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contracts> getContractById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.contractService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Contracts> createContract(@RequestBody Contracts contracts) {
        Date checkinTime = contracts.getBeginTime();
        Date checkoutTime = contracts.getEndTime();
        Date currentTime = new Date();
        Boolean isTimeValid;
        isTimeValid = contractService.checkContractTime(currentTime,checkinTime,checkoutTime);
        System.out.println(currentTime);
        return ResponseEntity.ok(this.contractService.create(contracts));
    }

    @PutMapping()
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
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    Láy tất cả hợp đồng theo hostID
    @GetMapping("host/{hostId}")
    public ResponseEntity<Iterable<Contracts>> getContractByHost(@PathVariable Long hostId) {
        return ResponseEntity.ok(this.contractService.findAllContractsByHostId(hostId));
    }

    //    Láy tất cả hợp đồng theo renterID
    @GetMapping("renter/{renterId}")
    public ResponseEntity<Iterable<Contracts>> getContractRenter(@PathVariable Long renterId) {
        return ResponseEntity.ok(this.contractService.findAllContractsByRenterId(renterId));
    }
}
