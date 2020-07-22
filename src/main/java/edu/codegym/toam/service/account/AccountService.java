package edu.codegym.toam.service.account;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService{
    @Autowired
    AccountRepository accountRepository;

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
        return accountRepository.findAccountByUsername(username);
    }
}
