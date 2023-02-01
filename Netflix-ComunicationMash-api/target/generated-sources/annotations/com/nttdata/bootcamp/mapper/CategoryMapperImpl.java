package com.nttdata.bootcamp.mapper;

import com.nttdata.bootcamp.persistence.entity.CategoryEntity;
import com.nttdata.bootcamp.service.responseModel.CategoryRest;
import com.nttdata.bootcamp.service.responseModel.restCategory.CategoryRestPost;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-01T16:33:02+0100",
    comments = "version: 1.4.0.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20221215-1352, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryEntity mapToEntity(CategoryRest rest) {
        if ( rest == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        return categoryEntity;
    }

    @Override
    public CategoryRest mapToRest(CategoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CategoryRest categoryRest = new CategoryRest();

        return categoryRest;
    }

    @Override
    public CategoryEntity mapToEntity(CategoryRestPost rest) {
        if ( rest == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        return categoryEntity;
    }

    @Override
    public CategoryRestPost mapToRestPost(CategoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CategoryRestPost categoryRestPost = new CategoryRestPost();

        return categoryRestPost;
    }
}
