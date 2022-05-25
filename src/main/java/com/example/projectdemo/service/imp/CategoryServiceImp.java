package com.example.projectdemo.service.imp;

import com.example.projectdemo.model.entity.Category;
import com.example.projectdemo.repository.CategoryRepository;
import com.example.projectdemo.service.inter.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements ICategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> getAll() {
        return repository.getAll();
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Category> findAllByName(String name) {
        return repository.findAllByName(name);
    }

    @Override
    public Optional<Category> findAllByNameToEdit(String name, Integer id) {
        return repository.findAllByNameToEdit(name, id);
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }
}
