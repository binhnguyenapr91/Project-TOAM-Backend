package edu.codegym.toam.controller;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/account")
@CrossOrigin("*")
public class AccountRestController {
    @Autowired
    IAccountService accountService;

    @GetMapping
    public ResponseEntity<Iterable<Account>> getAccounts() {
        return ResponseEntity.ok(this.accountService.findAll());
    }
}
