package com.example.projectdemo.service.inter;

import com.example.projectdemo.model.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAccountService extends UserDetailsService {

    Account findByUsername(String username);
}
