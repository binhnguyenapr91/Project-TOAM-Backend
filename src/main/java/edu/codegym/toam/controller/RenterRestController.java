package edu.codegym.toam.controller;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.Contracts;
import edu.codegym.toam.service.account.IAccountService;
import edu.codegym.toam.service.contract.IContractService;
import edu.codegym.toam.service.properties.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/renter")
@CrossOrigin(origins = "*")
public class RenterRestController {

    @Autowired
    IAccountService accountService;
    @Autowired
    IContractService contractService;
    @Autowired
    IPropertiesService propertiesService;

    //    Hiển thị  profile của thằng renter vừa đăng nhập
//    @GetMapping("/properties")
//    public ResponseEntity<Iterable<Properties>> hostProperties() {
//        Account currentRenter = getCurrentAccount();
//        Long id = currentRenter.getId();
//        return ResponseEntity.ok(this.propertiesService.findAllPropertiesByHostId(id));
//    }

    //Láy tất cả hợp đồng của thằng renter này
    @GetMapping("/properties/contract")
    public ResponseEntity<Iterable<Contracts>> Contract() {
        Account currentRenter = getCurrentAccount();
        Long id = currentRenter.getId();
        return ResponseEntity.ok(this.contractService.findAllContractsByRenterId(id));
    }

    private Account getCurrentAccount() {
        Authentication loggedAccount = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedAccount.getName();
        return this.accountService.findByUsername(username);
    }
}
