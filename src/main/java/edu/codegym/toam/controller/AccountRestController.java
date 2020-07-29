package edu.codegym.toam.controller;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.NotActiveException;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "*")
public class AccountRestController {
    @Autowired
    IAccountService accountService;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Iterable<Account>> getAccounts() {
        return ResponseEntity.ok(this.accountService.findAll());
    }

    //Lấy danh sách tất cả những thằng chủ nhà
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/host")
    public ResponseEntity<Iterable<Account>> getHost() {
        return ResponseEntity.ok(this.accountService.findAllHost());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/renter")
    public ResponseEntity<Iterable<Account>> getRenter() {
        return ResponseEntity.ok(this.accountService.findAllRenter());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_RENTER','ROLE_HOST')")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        try {
            return ResponseEntity.ok(this.accountService.update(account));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    //    Chuyển đổi trạng thái account (từ block sang không block và ngược lại)
    @PostMapping("/edit/{accountId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_RENTER','ROLE_HOST')")
    public ResponseEntity<Account> changeAccountStatus(@PathVariable Long accountId) {
        try {
            this.accountService.changeAccountStatus(accountId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //    Xóa account điều kiện ràng buộc là ko có bất kỳ hóa đơn nào
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("delete/{id}")
    public ResponseEntity<String> removePropertyById(@PathVariable Long id) {
        try {
            if (this.accountService.findById(id) == null) throw new Exception();
            if (!this.accountService.checkAccountConstraint(id)) {
                this.accountService.removeById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else throw new NotActiveException();
        } catch (NotActiveException nae) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
