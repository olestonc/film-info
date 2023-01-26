package com.example.demo.controller.rest;

import org.springframework.data.domain.Pageable;

import com.example.demo.controller.rest.model.CategoryRest;
import com.example.demo.controller.rest.model.D4iPageRest;
import com.example.demo.controller.rest.model.NetflixResponse;
import com.example.demo.controller.rest.model.restCategory.CategoryRestPost;
import com.example.demo.exception.NetflixException;

public interface CategoryControllerRest {

    NetflixResponse<D4iPageRest<CategoryRest>> getAllCategories(int page, int size, Pageable pageable)
            throws NetflixException;

    NetflixResponse<CategoryRest> getCategoryById(Long id) throws NetflixException;

    NetflixResponse<CategoryRestPost> createCategory(CategoryRestPost category) throws NetflixException;

    NetflixResponse<CategoryRestPost> updateCategory(CategoryRestPost category) throws NetflixException;

    void deleteCategory(Long id) throws NetflixException;

}
