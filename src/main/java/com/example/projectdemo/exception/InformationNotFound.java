package com.example.projectdemo.exception;

public class InformationNotFound extends RuntimeException{

    private String message;

    public InformationNotFound(String message){
        super(message);
        this.message = message;
    }
}
