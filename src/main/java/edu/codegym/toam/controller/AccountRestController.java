package edu.codegym.toam.controller;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
@CrossOrigin(origins = "*")
public class AccountRestController {
    @Autowired
    IAccountService accountService;

    @GetMapping("")
    public ResponseEntity<Iterable<Account>> getAccounts() {
        return ResponseEntity.ok(this.accountService.findAll());
    }

    //    Lấy danh sách tất cả những thằng chủ nhà
    @GetMapping("/host")
    public ResponseEntity<Iterable<Account>> getHost() {
        return ResponseEntity.ok(this.accountService.findAllHost());
    }

    //Lấy danh sách tất cả những thằng thuê nhà
    @GetMapping("/renter")
    public ResponseEntity<Iterable<Account>> getRenter() {
        return ResponseEntity.ok(this.accountService.findAllRenter());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.accountService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {

        try {
            return ResponseEntity.ok(this.accountService.create(account));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PutMapping()
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {


        try {
            return ResponseEntity.ok(this.accountService.update(account));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeBlogById(@PathVariable Long id) {
        try {
            this.accountService.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
