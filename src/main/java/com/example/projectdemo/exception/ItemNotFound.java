package com.example.projectdemo.exception;

public class ItemNotFound extends RuntimeException{

    private String message;

    public ItemNotFound(String message){
        super(message);
        this.message = message;
    }
}
