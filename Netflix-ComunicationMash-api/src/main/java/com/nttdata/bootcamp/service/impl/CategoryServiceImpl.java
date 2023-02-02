package com.nttdata.bootcamp.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.bootcamp.exception.NetflixException;
import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.exception.error.ErrorDto;
import com.nttdata.bootcamp.mapper.CategoryMapper;
import com.nttdata.bootcamp.persistence.entity.CategoryEntity;
import com.nttdata.bootcamp.persistence.repository.CategoryRepository;
import com.nttdata.bootcamp.service.CategoryService;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.D4iPaginationInfo;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.reponseActor.ActorResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseCategory.CategoryResponseDTO;
import com.nttdata.bootcamp.util.constant.CommonConstantsUtils;
import com.nttdata.bootcamp.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    public NetflixResponse<D4iPageRest<CategoryResponseDTO>> getAllCategorys(Pageable pageable) {
        Page<CategoryEntity> page = categoryRepository.findAll(pageable);
        return new NetflixResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(page.map(categoryMapper::mapEntityToResponseDTO).getContent().toArray(
                        CategoryResponseDTO[]::new),
                        new D4iPaginationInfo(page.getNumber(),
                                pageable.getPageSize(),
                                page.getTotalPages())));
    }

    @Override
    @Transactional(readOnly = true)
    public NetflixResponse<CategoryResponseDTO> getCategoryById(Long id) {
        try {
            CategoryEntity category = categoryRepository.findById(id)
                    .orElseThrow(
                            () -> new NetflixNotFoundException(
                                    new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            return new NetflixResponse<>(HttpStatus.OK.toString(),
                    String.valueOf(HttpStatus.OK.value()), CommonConstantsUtils.OK,
                    categoryMapper.mapEntityToResponseDTO(category));

        } catch (NetflixNotFoundException netflixNotFoundException) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<CategoryResponseDTO> createCategory(CategoryResponseDTO categoryRest) {
        CategoryEntity categoryEntity = categoryMapper.mapResponseDTOToEntity(categoryRest);
        // Donde se valida que un category no tiene datos inválidos?
        // Aquí sería un posible sitio para hacerlo y en caso de dato inválido
        // devolvemos la respuesta correspondiente
        categoryRepository.save(categoryEntity);

        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, categoryRest);
    }

    @Override
    @Transactional
    public NetflixResponse<CategoryResponseDTO> updateCategory(CategoryResponseDTO categoryRest) {
        CategoryEntity categoryOld = categoryRepository.findById(categoryRest.getActorId()).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        CategoryEntity categoryNew = categoryMapper.mapResponseDTOToEntity(categoryRest);

        categoryNew.setActorName(categoryOld.getActorName());
        categoryRepository.save(categoryNew);
        return categoryMapper.mapEntityToResponseDTO(categoryNew);
    }

    @Override
    @Transactional
    public NetflixResponse<CategoryResponseDTO> deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return new NetflixResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()), CommonConstantsUtils.OK);

    }

}