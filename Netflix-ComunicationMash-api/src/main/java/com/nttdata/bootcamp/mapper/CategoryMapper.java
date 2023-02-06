package com.nttdata.bootcamp.mapper;

import org.mapstruct.Mapper;


import com.nttdata.bootcamp.persistence.entity.CategoryEntity;
import com.nttdata.bootcamp.service.responseModel.responseCategory.CategoryResponseDTO;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryEntity mapResponseDTOToEntity(CategoryResponseDTO rest);

    CategoryResponseDTO mapEntityToResponseDTO(CategoryEntity entity);
}
