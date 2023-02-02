package com.nttdata.bootcamp.mapper;

import com.nttdata.bootcamp.persistence.entity.CategoryEntity;
import com.nttdata.bootcamp.service.responseModel.responseCategory.CategoryResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-02T14:31:20+0100",
    comments = "version: 1.4.0.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20221215-1352, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryEntity mapResponseDTOToEntity(CategoryResponseDTO rest) {
        if ( rest == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategoryId( rest.getCategoryId() );
        categoryEntity.setCategoryName( rest.getCategoryName() );

        return categoryEntity;
    }

    @Override
    public CategoryResponseDTO mapEntityToResponseDTO(CategoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

        categoryResponseDTO.setCategoryId( entity.getCategoryId() );
        categoryResponseDTO.setCategoryName( entity.getCategoryName() );

        return categoryResponseDTO;
    }
}
