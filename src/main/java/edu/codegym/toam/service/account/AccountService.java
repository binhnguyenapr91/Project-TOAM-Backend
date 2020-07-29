package edu.codegym.toam.service.account;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.Contracts;
import edu.codegym.toam.model.Role;
import edu.codegym.toam.repository.AccountRepository;
import edu.codegym.toam.repository.ContractRepository;
import edu.codegym.toam.repository.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PropertiesRepository propertiesRepository;
    @Autowired
    ContractRepository contractRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findAccountByUsername(username).get();
    }

    @Override
    public Account update(Account account) {
//        String encoderPass = this.encoder.encode(account.getPassword());
//        account.setPassword(encoderPass);
        return accountRepository.save(account);
    }

    @Override
    public void removeById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account create(Account account) {
        account.setStatus(true);
        String encoderPass = this.encoder.encode(account.getPassword());
        account.setPassword(encoderPass);
        return accountRepository.save(account);
    }

    @Override
    public Iterable<Account> findAllHost() {
        Role host = new Role();
        host.setId((long) 2);
        return accountRepository.findAccountByRoles(host);
    }

    @Override
    public Iterable<Account> findAllRenter() {
        Role renter = new Role();
        renter.setId((long) 3);
        return accountRepository.findAccountByRoles(renter);
    }

    @Override
    public void changeAccountStatus(Long accountId) {
        Account account = accountRepository.findById(accountId).get();
        account.changeStatus();
        accountRepository.save(account);
    }

    @Override
    public boolean checkAccountConstraint(Long id) {
        Iterable<Contracts> renterContract = contractRepository.findContractsByProperties_Host_Id(id);
        Iterable<Contracts> hostContract = contractRepository.findContractsByRenter_Id(id);
        return renterContract.iterator().hasNext() || hostContract.iterator().hasNext();
    }

    @Override
    public Iterable<Account> getAllByRoleName(String name) {
        return accountRepository.getAccountByRoleName(name);
    }

}
