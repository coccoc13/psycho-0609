package com.example.projectdemo.service.inter;

import com.example.projectdemo.model.entity.Category;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    List<Category> getAll();
    Optional<Category> findById(Integer id);
    Optional<Category> findAllByName(String name);
    Optional<Category> findAllByNameToEdit(@Param("name") String name, @Param("id") Integer id);
    Category save(Category category);
}
