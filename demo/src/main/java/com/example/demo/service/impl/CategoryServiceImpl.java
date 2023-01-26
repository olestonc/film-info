package com.example.demo.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.rest.model.CategoryRest;
import com.example.demo.controller.rest.model.restCategory.CategoryRestPost;
import com.example.demo.exception.NetflixException;
import com.example.demo.exception.NetflixNotFoundException;
import com.example.demo.exception.error.ErrorDto;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.persistence.entity.CategoryEntity;
import com.example.demo.persistence.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryRest> getAllCategorys(Pageable pageable) throws NetflixException {
        Page<CategoryEntity> page = categoryRepository.findAll(pageable);
        return page.map(categoryMapper::mapToRest);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryRest getCategoryById(Long id) throws NetflixException {
        /*
         * Equivale a usar la clase optional para recibir de repositorio y
         * if(optional.get()==null) then throws Not Found
         */
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return categoryMapper.mapToRest(category);
    }

    @Override
    @Transactional
    public CategoryRestPost createCategory(CategoryRestPost categoryRest) throws NetflixException {
        CategoryEntity categoryEntity = categoryMapper.mapToEntity(categoryRest);
        categoryRepository.save(categoryEntity);// Donde se valida que un category no tiene datos inválidos?
        return categoryRest;
    }

    @Override
    @Transactional
    public CategoryRestPost updateCategory(CategoryRestPost categoryRest) throws NetflixException {
        CategoryEntity categoryOld = categoryRepository.findById(categoryRest.getId()).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        CategoryEntity categoryNew = categoryMapper.mapToEntity(categoryRest);
   
        categoryNew.setName(categoryOld.getName());
        categoryRepository.save(categoryNew);
        return categoryMapper.mapToRestPost(categoryNew);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) throws NetflixException {
        categoryRepository.deleteById(id);

    }

}