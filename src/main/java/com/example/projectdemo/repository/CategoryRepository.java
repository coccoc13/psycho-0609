package com.example.projectdemo.repository;

import com.example.projectdemo.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findAllByName(String name);

    @Query("select c from Category c where c.name = :name and c.id <> :id")
    Optional<Category> findAllByNameToEdit(@Param("name") String name, @Param("id") Integer id);

    @Query("select c from Category c where c.delFlag = 1")
    List<Category> getAll();
}
