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
@RequestMapping("api/host")
@CrossOrigin(origins = "*")
public class HostRestController {

    @Autowired
    IAccountService accountService;
    @Autowired
    IContractService contractService;
    @Autowired
    IPropertiesService propertiesService;

    //Lấy thông tin của thằng host vừa đăng nhập
    @GetMapping("/information")
    public ResponseEntity<Account> hostInfo() {
        Account currentHost = getCurrentAccount();
        Long id = currentHost.getId();
        return ResponseEntity.ok(this.accountService.findById(id));
    }

    //Sửa đổi host info
    @PutMapping("/information/edit")
    public ResponseEntity<Account> hostInfoEdit(@RequestBody Account updatedAccount) {
        Account currentHost = getCurrentAccount();
        Long id = currentHost.getId();
        //Đảm bảo update vào đúng info của thằng host vừa đăng nhập
        updatedAccount.setId(id);
        try {
            return ResponseEntity.ok(this.accountService.update(updatedAccount));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //    Hiển thị tất cả properties của thằng host vừa đăng nhập
    @GetMapping("/properties")
    public ResponseEntity<Iterable<Properties>> hostProperties() {
        Account currentHost = getCurrentAccount();
        Long id = currentHost.getId();
        return ResponseEntity.ok(this.propertiesService.findAllPropertiesByHostId(id));
    }

    //    Lấy Properties của currentHost theo propertyId
    @GetMapping("properties/{id}")
    public ResponseEntity<Properties> getPropertiesById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.propertiesService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    Tạo properties từ current host
    @PostMapping("/properties/create")
    public ResponseEntity<Properties> createProperties(@RequestBody Properties properties) {
        Account currentHost = getCurrentAccount();
        Long id = currentHost.getId();
        properties.setHost(currentHost);

        try {
            System.out.println(properties.getId());
            return ResponseEntity.ok(this.propertiesService.create(properties));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

////    Sửa thông tin nhà
//    @PutMapping("properties/{id}/update")
//    public ResponseEntity<Properties> updateProperties(@RequestBody Properties properties) {
//        Account currentHost = getCurrentAccount();
//        Long id = currentHost.getId();
//        try {
//            return ResponseEntity.ok(this.propertiesService.update(properties));
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
//    }

    //    Tìm properties theo quận,thành phố, tên properties, địa chỉ
    @GetMapping("/filter/{key}")
    public ResponseEntity<Iterable<Properties>> searchForProperties(@PathVariable String key) {
        return ResponseEntity.ok(this.propertiesService.filterProperties(key));
    }

    //    Phân loại nhà của thằng host vừa đăng nhập
    @GetMapping("/properties/propertyType/{propertyTypeId}")
    public ResponseEntity<Iterable<Properties>> searchForProperties(@PathVariable Long propertyTypeId) {
        Account currentHost = getCurrentAccount();
        Long hostId = currentHost.getId();
        return ResponseEntity.ok(this.propertiesService.findPropertiesByHostIdAndType(hostId, propertyTypeId));
    }

    //Láy tất cả hợp đồng của thằng host này
    @GetMapping("/contracts")
    public ResponseEntity<Iterable<Contracts>> Contract() {
        Account currentHost = getCurrentAccount();
        Long id = currentHost.getId();
        return ResponseEntity.ok(this.contractService.findAllContractsByHostId(id));
    }

    private Account getCurrentAccount() {
        Authentication loggedAccount = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedAccount.getName();
        return this.accountService.findByUsername(username);
    }
}
