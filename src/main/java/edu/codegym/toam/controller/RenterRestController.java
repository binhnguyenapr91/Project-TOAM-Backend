package edu.codegym.toam.controller;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.Contracts;
import edu.codegym.toam.model.Properties;
import edu.codegym.toam.service.account.IAccountService;
import edu.codegym.toam.service.contract.IContractService;
import edu.codegym.toam.service.properties.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/renter")
public class RenterRestController {
    @Autowired
    IAccountService accountService;
    @Autowired
    IContractService contractService;
    @Autowired
    IPropertiesService propertiesService;

    //Lấy thông tin của thằng host vừa đăng nhập
    @GetMapping("/information")
    public ResponseEntity<Account> renterInfo() {
        Account currentRenter = getCurrentAccount();
        Long id = currentRenter.getId();
        return ResponseEntity.ok(this.accountService.findById(id));
    }

    //Sửa đổi renter info
    @PutMapping("/information/edit")
    public ResponseEntity<Account> renterInfoEdit(@RequestBody Account updatedAccount) {
        Account currentHost = getCurrentAccount();
        Long id = currentHost.getId();
        //Đảm bảo update vào đúng info của thằng renter vừa đăng nhập
        updatedAccount.setId(id);
        try {
            return ResponseEntity.ok(this.accountService.update(updatedAccount));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //Láy tất cả hợp đồng của thằng host này
    @GetMapping("/contracts")
    public ResponseEntity<Iterable<Contracts>> Contract() {
        Account currentHost = getCurrentAccount();
        Long id = currentHost.getId();
        return ResponseEntity.ok(this.contractService.findAllContractsByRenterId(id));
    }

//    //    Tạo contracts từ current renter
//    @PostMapping("/contracts/create")
//    public ResponseEntity<Contracts> createContract(@RequestBody Contracts contracts) {
////        Account currentHost = getCurrentAccount();
////        contracts.setRenter(currentHost);
////        System.out.println(currentHost.getId());
//        try {
//            return ResponseEntity.ok(this.contractService.create(contracts));
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
//    }

    //    Tạo contracts từ current renter
    @PostMapping("/contracts/create")
    public ResponseEntity<Contracts> createContract(@RequestBody Contracts contracts) {
        Account currentHost = getCurrentAccount();
        Long id = currentHost.getId();
        contracts.setRenter(currentHost);
        contracts.setStatus(true);
        try {
            System.out.println(contracts.getId());
            return ResponseEntity.ok(this.contractService.create(contracts));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //    Hủy contract (change contract status)
    @PutMapping("/contracts/status")
    public ResponseEntity<Contracts> status(@RequestBody Contracts contracts) {
        Account currentHost = getCurrentAccount();
        Long id = currentHost.getId();
        contracts.setRenter(currentHost);
        contracts.setStatus(true);
        try {
            System.out.println(contracts.getId());
            return ResponseEntity.ok(this.contractService.update(contracts));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //    Hiển thị tất cả properties có trong contract của thằng renter vừa đăng nhập
    @GetMapping("/properties")
    public ResponseEntity<Iterable<Properties>> hostProperties() {
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
