package edu.codegym.toam.service.account;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
public class AccountDetailService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUsername(username);
        if (account == null) {
            try {
                throw new UserPrincipalNotFoundException(username);
            } catch (UserPrincipalNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new CustomAccountDetail(account);
    }
}
