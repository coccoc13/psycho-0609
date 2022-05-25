package com.example.projectdemo.exception;

public class ItemIsExisted extends RuntimeException{

    private String message;

    public ItemIsExisted(String message) {
        super(message);
        this.message = message;
    }
}
