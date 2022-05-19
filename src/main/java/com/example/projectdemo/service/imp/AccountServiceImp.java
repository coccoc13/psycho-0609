package com.example.projectdemo.service.imp;


import com.example.projectdemo.model.entity.Account;
import com.example.projectdemo.model.userdetail.AccountPrincipal;
import com.example.projectdemo.repository.AccountRepository;
import com.example.projectdemo.service.inter.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountServiceImp implements IAccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findAccountByUsername(username);
        if (account == null){  throw new UsernameNotFoundException("");}
        SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(account.getRoleType().getValue().toString());
        return new AccountPrincipal(account.getId(), account.getName(), account.getRoleType().getValue(), account.getUsername(), account.getPassword(),
                true, true, true, true,
                Collections.singleton(authorities));
    }
}
