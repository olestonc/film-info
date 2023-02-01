package com.nttdata.bootcamp.mapper;

import org.mapstruct.Mapper;

import com.nttdata.bootcamp.persistence.entity.CategoryEntity;
import com.nttdata.bootcamp.service.responseModel.CategoryRest;
import com.nttdata.bootcamp.service.responseModel.restCategory.CategoryRestPost;



@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryEntity mapToEntity(CategoryRest rest);

    CategoryRest mapToRest(CategoryEntity entity);

    CategoryEntity mapToEntity(CategoryRestPost rest);

    CategoryRestPost mapToRestPost(CategoryEntity entity);
}
