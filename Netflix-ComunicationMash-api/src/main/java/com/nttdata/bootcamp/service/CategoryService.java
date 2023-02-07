package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.responseCategory.CategoryResponseDTO;

public interface CategoryService {
    D4iPageRest<CategoryResponseDTO> getAllCategorys(Pageable pageable);

    CategoryResponseDTO getCategoryById(Long id) throws NetflixNotFoundException;

    CategoryResponseDTO createCategory(CategoryResponseDTO category);

    CategoryResponseDTO updateCategory(CategoryResponseDTO category) throws NetflixNotFoundException;

    void deleteCategory(Long id);

}
