package com.nttdata.bootcamp.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.exception.error.ErrorDto;
import com.nttdata.bootcamp.mapper.CategoryMapper;
import com.nttdata.bootcamp.persistence.entity.CategoryEntity;
import com.nttdata.bootcamp.persistence.repository.CategoryRepository;
import com.nttdata.bootcamp.service.CategoryService;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.D4iPaginationInfo;
import com.nttdata.bootcamp.service.responseModel.responseCategory.CategoryResponseDTO;
import com.nttdata.bootcamp.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
        private final CategoryRepository categoryRepository;

        private final CategoryMapper categoryMapper;

        @Override
        @Transactional(readOnly = true)
        public D4iPageRest<CategoryResponseDTO> getAllCategorys(Pageable pageable) {
                Page<CategoryEntity> page = categoryRepository.findAll(pageable);
                return new D4iPageRest<>(page.map(categoryMapper::mapEntityToResponseDTO).getContent().toArray(
                                                CategoryResponseDTO[]::new),
                                                new D4iPaginationInfo(page.getNumber(),
                                                                pageable.getPageSize(),
                                                                page.getTotalPages()));
        }

        @Override
        @Transactional(readOnly = true)
        public CategoryResponseDTO getCategoryById(Long id) throws NetflixNotFoundException {

                CategoryEntity category = categoryRepository.findById(id)
                                .orElseThrow(() -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

                return categoryMapper.mapEntityToResponseDTO(category);

        }

        @Override
        @Transactional
        public CategoryResponseDTO createCategory(CategoryResponseDTO categoryRest) {
                CategoryEntity categoryEntity = categoryMapper.mapResponseDTOToEntity(categoryRest);
                // Donde se valida que un category no tiene datos inválidos?
                // Aquí sería un posible sitio para hacerlo y en caso de dato inválido
                // devolvemos la respuesta correspondiente
                categoryRepository.save(categoryEntity);

                return categoryRest;
        }

        @Override
        @Transactional
        public CategoryResponseDTO updateCategory(CategoryResponseDTO categoryRest) throws NetflixNotFoundException {

                CategoryEntity categoryOld = categoryRepository.findById(categoryRest.getCategoryId())
                                .orElseThrow(() -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
                CategoryEntity categoryNew = categoryMapper.mapResponseDTOToEntity(categoryRest);

                categoryOld.setCategoryName(categoryNew.getCategoryName());
                categoryRepository.save(categoryOld);
                return categoryMapper.mapEntityToResponseDTO(categoryOld);
        }

        @Override
        @Transactional
        public void deleteCategory(Long id) {
                categoryRepository.deleteById(id);
            

        }

}