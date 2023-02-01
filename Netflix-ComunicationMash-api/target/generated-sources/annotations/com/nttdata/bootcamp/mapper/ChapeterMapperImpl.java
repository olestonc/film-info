package com.nttdata.bootcamp.mapper;

import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;
import com.nttdata.bootcamp.service.responseModel.ChapeterRest;
import com.nttdata.bootcamp.service.responseModel.restChapeter.ChapeterRestPost;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-01T16:33:02+0100",
    comments = "version: 1.4.0.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20221215-1352, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class ChapeterMapperImpl implements ChapeterMapper {

    @Override
    public ChapeterEntity mapToEntity(ChapeterRest rest) {
        if ( rest == null ) {
            return null;
        }

        ChapeterEntity chapeterEntity = new ChapeterEntity();

        chapeterEntity.setName( rest.getName() );

        return chapeterEntity;
    }

    @Override
    public ChapeterRest mapToRest(ChapeterEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ChapeterRest chapeterRest = new ChapeterRest();

        chapeterRest.setName( entity.getName() );

        return chapeterRest;
    }

    @Override
    public ChapeterEntity mapToEntity(ChapeterRestPost rest) {
        if ( rest == null ) {
            return null;
        }

        ChapeterEntity chapeterEntity = new ChapeterEntity();

        chapeterEntity.setName( rest.getName() );

        return chapeterEntity;
    }

    @Override
    public ChapeterRestPost mapToRestPost(ChapeterEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ChapeterRestPost chapeterRestPost = new ChapeterRestPost();

        chapeterRestPost.setName( entity.getName() );

        return chapeterRestPost;
    }
}
