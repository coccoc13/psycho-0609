package com.example.projectdemo.controller;

import com.example.projectdemo.constant.MessageConstant;
import com.example.projectdemo.exception.AccountNotFound;
import com.example.projectdemo.model.request.LoginRequest;
import com.example.projectdemo.model.response.AccountResponse;
import com.example.projectdemo.model.userdetail.AccountPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Object authenticateUser(@RequestBody LoginRequest request){

        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(user);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            AccountPrincipal accountDetail = (AccountPrincipal) authentication.getPrincipal();
            return new ResponseEntity<>(new AccountResponse(accountDetail.getId(), accountDetail.getName(), accountDetail.getRole()), HttpStatus.OK);
        }catch (Exception e){
            throw new AccountNotFound(MessageConstant.ACCOUNT_NOT_FROUND);
        }

    }
}
