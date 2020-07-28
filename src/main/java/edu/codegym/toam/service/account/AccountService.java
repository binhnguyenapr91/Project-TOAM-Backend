package edu.codegym.toam.service.account;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.Role;
import edu.codegym.toam.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    AccountRepository accountRepository;
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
}
