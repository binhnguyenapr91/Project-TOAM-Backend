package edu.codegym.toam.controller;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.Properties;
import edu.codegym.toam.service.account.IAccountService;
import edu.codegym.toam.service.properties.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/host")
@CrossOrigin(origins = "*")
public class HostRestController {

    @Autowired
    IAccountService accountService;
    @Autowired
    IPropertiesService propertiesService;

    //    Hiển thị tất cả properties của thằng host vừa đăng nhập
    @GetMapping("/properties")
    public ResponseEntity<Iterable<Properties>> hostProperties() {
        Account currentHost = getCurrentAccount();
        Long id = currentHost.getId();
        return ResponseEntity.ok(this.propertiesService.findAllPropertiesByHostId(id));
    }

    //    Phân loại nhà của thằng host vừa đăng nhập
    @GetMapping("/properties/filter/{propertyTypeId}")
    public ResponseEntity<Iterable<Properties>> searchForProperties(@PathVariable Long propertyTypeId) {
        Account currentHost = getCurrentAccount();
        Long hostId = currentHost.getId();
        return ResponseEntity.ok(this.propertiesService.findPropertiesByHostIdAndType(hostId, propertyTypeId));
    }

    //Láy tất cả hợp đồng của thằng host này
    @GetMapping("/properties/contract")
    public ResponseEntity<Iterable<Properties>> Contract() {
        Account currentHost = getCurrentAccount();
        Long id = currentHost.getId();
        return ResponseEntity.ok(this.propertiesService.findAllPropertiesByHostId(id));
    }

    private Account getCurrentAccount() {
        Authentication loggedAccount = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedAccount.getName();
        return this.accountService.findByUsername(username);
    }
}
