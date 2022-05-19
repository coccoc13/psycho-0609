package com.example.projectdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projectdemo.model.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByUsername(String username);

}
