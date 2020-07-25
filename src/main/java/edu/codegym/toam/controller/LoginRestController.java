package edu.codegym.toam.controller;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.Properties;
import edu.codegym.toam.service.account.IAccountService;
import edu.codegym.toam.service.properties.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class LoginRestController {

    @Autowired
    IAccountService accountService;
    @Autowired
    IPropertiesService propertiesService;

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

//    Hiển thị tất cả properties của thằng host vừa đăng nhập
    @GetMapping("/host/properties")
    public ResponseEntity<Iterable<Properties>> hostProperties() {
        Account currentHost = getCurrentAccount();
        Long id = currentHost.getId();
        return ResponseEntity.ok(this.propertiesService.findAllPropertiesByHostId(id));
    }

    @GetMapping("/host/properties/filter/{propertyTypeId}")
//    Phân loại nhà của thằng host vừa đăng nhập
    public ResponseEntity<Iterable<Properties>> searchForProperties(@PathVariable Long propertyTypeId) {
        Account currentHost = getCurrentAccount();
        Long hostId = currentHost.getId();
        return ResponseEntity.ok(this.propertiesService.findPropertiesByHostIdAndType(hostId,propertyTypeId));
    }

    private Account getCurrentAccount() {
        Authentication loggedAccount = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedAccount.getName();
        return this.accountService.findByUsername(username);
    }
}
