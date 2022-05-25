package com.example.projectdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projectdemo.model.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByUsername(String username);

}
