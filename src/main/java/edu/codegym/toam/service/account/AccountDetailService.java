package edu.codegym.toam.service.account;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountDetailService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUsername(username);
        if (account ==null){
            try {
                throw new UserPrincipalNotFoundException(username);
            } catch (UserPrincipalNotFoundException e) {
                e.printStackTrace();
            }
        }
        String roleNames = this.accountRepository.findAccountByUsername(username).getRole().getName();
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(roleNames);
        grantList.add(authority);
        CustomAccountDetail customAccountDetail= new CustomAccountDetail();
        customAccountDetail.setAccount(account);
        customAccountDetail.setAuthorities(grantList);
        return customAccountDetail;
    }
}
