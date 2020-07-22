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
        return ResponseEntity.ok(this.accountService.create(account));
    }

    @PutMapping()
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        return ResponseEntity.ok(this.accountService.update(account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeBlogById(@PathVariable Long id) {
        try {
            this.accountService.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
