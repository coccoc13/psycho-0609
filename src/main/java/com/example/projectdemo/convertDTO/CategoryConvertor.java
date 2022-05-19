package com.example.projectdemo.convertDTO;

import com.example.projectdemo.model.dto.CategoryListDTO;
import com.example.projectdemo.model.entity.Category;

public class CategoryConvertor {

    public static CategoryListDTO toDTO(Category category) {
        return new CategoryListDTO(category.getId(), category.getName(), category.getCreatedAt(), category.getUpdateAt());
    }
}
