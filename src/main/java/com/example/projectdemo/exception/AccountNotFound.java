package com.example.projectdemo.exception;


public class AccountNotFound extends RuntimeException{

    private String message;

    public AccountNotFound(String message){
        super(message);
        this.message = message;
    }

}
