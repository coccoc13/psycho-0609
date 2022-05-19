package com.example.projectdemo.exception;

public class InformationExisted extends RuntimeException{

    private String message;

    public InformationExisted(String message) {
        super(message);
        this.message = message;
    }
}
