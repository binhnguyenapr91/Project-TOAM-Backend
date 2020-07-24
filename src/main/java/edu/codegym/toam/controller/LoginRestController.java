package edu.codegym.toam.controller;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class LoginRestController {

    @Autowired
    IAccountService accountService;

//    Gửi trả lại account vừa đăng nhập
    @PostMapping("/login")
    public HttpEntity<Account> loginSuccess() {
        try {
            Authentication loggedAccount = SecurityContextHolder.getContext().getAuthentication();
            String username = loggedAccount.getName();
            return ResponseEntity.ok(this.accountService.findByUsername(username));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
