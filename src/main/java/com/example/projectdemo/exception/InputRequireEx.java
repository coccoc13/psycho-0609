package com.example.projectdemo.exception;

public class InputRequireEx extends RuntimeException{

    String message;

    public InputRequireEx(String message) {
        super(message);
        this.message = message;
    }
}
