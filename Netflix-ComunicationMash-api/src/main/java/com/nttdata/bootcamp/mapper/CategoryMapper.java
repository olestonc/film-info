package com.nttdata.bootcamp.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.persistence.entity.CategoryEntity;
import com.nttdata.bootcamp.service.responseModel.responseCategory.CategoryResponseDTO;


@Component
@Mapper
public interface CategoryMapper {

    CategoryEntity mapResponseDTOToEntity(CategoryResponseDTO rest);

    CategoryResponseDTO mapEntityToResponseDTO(CategoryEntity entity);
}
