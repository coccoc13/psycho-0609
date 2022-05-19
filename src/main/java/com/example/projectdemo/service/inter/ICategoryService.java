package com.example.projectdemo.service.inter;

import com.example.projectdemo.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    List<Category> getAll();
    Optional<Category> findById(Integer id);
}
