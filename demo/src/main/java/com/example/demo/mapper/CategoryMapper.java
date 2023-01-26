package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.controller.rest.model.CategoryRest;
import com.example.demo.controller.rest.model.restCategory.CategoryRestPost;
import com.example.demo.persistence.entity.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryEntity mapToEntity(CategoryRest rest);

    CategoryRest mapToRest(CategoryEntity entity);

    CategoryEntity mapToEntity(CategoryRestPost rest);

    CategoryRestPost mapToRestPost(CategoryEntity entity);
}
