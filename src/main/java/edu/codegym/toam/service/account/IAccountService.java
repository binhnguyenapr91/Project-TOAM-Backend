package edu.codegym.toam.service.account;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.Role;

public interface IAccountService {
    Iterable<Account> findAll();

    Iterable<Account> findAllHost();

    Iterable<Account> findAllRenter();

    Account findById(Long id);

    Account findByUsername(String username);

    Account update(Account account);

    void removeById(Long id);

    Account create(Account account);

    void changeAccountStatus(Long accountId);

    boolean checkAccountConstraint(Long id);


}
