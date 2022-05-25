package com.example.projectdemo.exception;

public class ItemCannotDelete extends RuntimeException{
    String message;

    public ItemCannotDelete(String message) {
        super(message);
        this.message = message;
    }
}
