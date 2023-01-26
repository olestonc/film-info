package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.dao.CategoryDao;
import com.example.demo.persistence.entity.CategoryEntity;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao dao;

    @Override
    public List<CategoryEntity> getAllCategories() {
        return dao.findAll();
    }

    @Override
    public Optional<CategoryEntity> getCategoryById(Long id) {
        return dao.findById(id);
    }
    
    @Override
    public CategoryEntity save(CategoryEntity category) {
        return dao.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        dao.deleteById(id);// NO FEEDBACK
    }


}