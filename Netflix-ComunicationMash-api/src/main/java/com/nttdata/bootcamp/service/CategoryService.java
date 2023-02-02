package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.responseCategory.CategoryResponseDTO;

public interface CategoryService {
    NetflixResponse<D4iPageRest<CategoryResponseDTO>> getAllCategorys(Pageable pageable);

    NetflixResponse<CategoryResponseDTO> getCategoryById(Long id);

    NetflixResponse<CategoryResponseDTO> createCategory(CategoryResponseDTO category);

    NetflixResponse<CategoryResponseDTO> updateCategory(CategoryResponseDTO category);

    NetflixResponse<CategoryResponseDTO> deleteCategory(Long id);

}
