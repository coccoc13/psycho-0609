package com.example.projectdemo.controller.admin;

import com.example.projectdemo.convertDTO.CategoryConvertor;
import com.example.projectdemo.model.entity.Category;
import com.example.projectdemo.service.inter.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryAdminController {

    @Autowired
    private ICategoryService service;

    @GetMapping
    public Object get() {
        List<Category> list = service.getAll();
        return new ResponseEntity<>(list.stream().map(CategoryConvertor::toDTO).collect(Collectors.toList()), HttpStatus.OK);
    }
}
