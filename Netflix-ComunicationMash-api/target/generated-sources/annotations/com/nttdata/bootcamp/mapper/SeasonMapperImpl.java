package com.nttdata.bootcamp.mapper;

import com.nttdata.bootcamp.persistence.entity.SeasonEntity;
import com.nttdata.bootcamp.service.responseModel.SeasonRest;
import com.nttdata.bootcamp.service.responseModel.restSeason.SeasonRestPost;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-01T16:33:02+0100",
    comments = "version: 1.4.0.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20221215-1352, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class SeasonMapperImpl implements SeasonMapper {

    @Override
    public SeasonEntity mapToEntity(SeasonRest rest) {
        if ( rest == null ) {
            return null;
        }

        SeasonEntity seasonEntity = new SeasonEntity();

        return seasonEntity;
    }

    @Override
    public SeasonRest mapToRest(SeasonEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SeasonRest seasonRest = new SeasonRest();

        return seasonRest;
    }

    @Override
    public SeasonEntity mapToEntity(SeasonRestPost rest) {
        if ( rest == null ) {
            return null;
        }

        SeasonEntity seasonEntity = new SeasonEntity();

        return seasonEntity;
    }

    @Override
    public SeasonRestPost mapToRestPost(SeasonEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SeasonRestPost seasonRestPost = new SeasonRestPost();

        return seasonRestPost;
    }
}
