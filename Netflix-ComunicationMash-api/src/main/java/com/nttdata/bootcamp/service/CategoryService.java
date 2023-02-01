package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.nttdata.bootcamp.exception.NetflixException;
import com.nttdata.bootcamp.service.responseModel.CategoryRest;
import com.nttdata.bootcamp.service.responseModel.restCategory.CategoryRestPost;

public interface CategoryService {
    Page<CategoryRest> getAllCategorys(Pageable pageable) throws NetflixException;

    CategoryRest getCategoryById(Long id) throws NetflixException;

    CategoryRestPost createCategory(CategoryRestPost category) throws NetflixException;

    CategoryRestPost updateCategory(CategoryRestPost category) throws NetflixException;

    void deleteCategory(Long id) throws NetflixException;

}
