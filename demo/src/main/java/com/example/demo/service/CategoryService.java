package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.persistence.entity.CategoryEntity;

public interface CategoryService {
    List<CategoryEntity> getAllCategories();

    Optional<CategoryEntity> getCategoryById(Long id);

    CategoryEntity save(CategoryEntity Category);

    void deleteCategory(Long id) ;

}
