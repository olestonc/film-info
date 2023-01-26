package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.controller.rest.model.CategoryRest;
import com.example.demo.controller.rest.model.restCategory.CategoryRestPost;
import com.example.demo.exception.NetflixException;

public interface CategoryService {
    Page<CategoryRest> getAllCategorys(Pageable pageable) throws NetflixException;

    CategoryRest getCategoryById(Long id) throws NetflixException;

    CategoryRestPost createCategory(CategoryRestPost category) throws NetflixException;

    CategoryRestPost updateCategory(CategoryRestPost category) throws NetflixException;

    void deleteCategory(Long id) throws NetflixException;

}
