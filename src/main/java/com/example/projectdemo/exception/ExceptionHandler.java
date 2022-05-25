package com.example.projectdemo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(AccountNotFound.class)
    public Map<String, Object> handle(AccountNotFound e) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", e.getMessage());
        return result;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(ItemNotFound.class)
    public Map<String, Object> handle(ItemNotFound e) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", e.getMessage());
        return result;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ItemIsExisted.class)
    public Map<String, Object> handle(ItemIsExisted e) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", e.getMessage());
        return result;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(InputRequireEx.class)
    public Map<String, Object> handle(InputRequireEx e) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", e.getMessage());
        return result;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ItemCannotDelete.class)
    public Map<String, Object> handle(ItemCannotDelete e) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", e.getMessage());
        return result;
    }
}
