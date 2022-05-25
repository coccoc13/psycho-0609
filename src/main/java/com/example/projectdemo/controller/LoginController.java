package com.example.projectdemo.controller;

import com.example.projectdemo.configuration.jwt.JwtTokenProvider;
import com.example.projectdemo.constant.MessageConstant;
import com.example.projectdemo.exception.AccountNotFound;
import com.example.projectdemo.model.entity.Account;
import com.example.projectdemo.model.request.LoginRequest;
import com.example.projectdemo.model.response.AccountResponse;
import com.example.projectdemo.model.response.LoginResponse;
import com.example.projectdemo.model.userdetail.AccountPrincipal;
import com.example.projectdemo.service.inter.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAccountService service;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @PostMapping("/login")
    public Object authenticateUser(@RequestBody LoginRequest request) {

        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(user);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            AccountPrincipal accountDetail = (AccountPrincipal) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(accountDetail);
            return new ResponseEntity<>(new LoginResponse(jwt), HttpStatus.OK);
        } catch (Exception e) {
            throw new AccountNotFound(MessageConstant.ACCOUNT_NOT_FROUND);
        }

    }

    @GetMapping("/api/user")
    public Object userInfor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountPrincipal userDetails = (AccountPrincipal) auth.getPrincipal();
        Account account = service.findByUsername(userDetails.getUsername());
        return new ResponseEntity<>(new AccountResponse(account.getId(), account.getName(), account.getRoleType().getValue()), HttpStatus.OK);
    }
}
