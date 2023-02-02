package com.nttdata.bootcamp.mapper;

import com.nttdata.bootcamp.persistence.entity.TvShowEntity;
import com.nttdata.bootcamp.service.responseModel.TvShowRest;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowRestCategory;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowRestPost;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowRestSeason;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowRestShort;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-02T14:21:41+0100",
    comments = "version: 1.4.0.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20221215-1352, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class TvShowMapperImpl implements TvShowMapper {

    @Override
    public TvShowEntity mapToEntity(TvShowRest rest) {
        if ( rest == null ) {
            return null;
        }

        TvShowEntity tvShowEntity = new TvShowEntity();

        return tvShowEntity;
    }

    @Override
    public TvShowRest mapToRest(TvShowEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TvShowRest tvShowRest = new TvShowRest();

        return tvShowRest;
    }

    @Override
    public TvShowEntity mapToEntity(TvShowRestPost rest) {
        if ( rest == null ) {
            return null;
        }

        TvShowEntity tvShowEntity = new TvShowEntity();

        return tvShowEntity;
    }

    @Override
    public TvShowRestPost mapToRestPost(TvShowEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TvShowRestPost tvShowRestPost = new TvShowRestPost();

        return tvShowRestPost;
    }

    @Override
    public TvShowEntity mapToEntity(TvShowRestShort rest) {
        if ( rest == null ) {
            return null;
        }

        TvShowEntity tvShowEntity = new TvShowEntity();

        return tvShowEntity;
    }

    @Override
    public TvShowRestShort mapToRestShort(TvShowEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TvShowRestShort tvShowRestShort = new TvShowRestShort();

        return tvShowRestShort;
    }

    @Override
    public TvShowRestSeason mapToRestSeason(TvShowEntity tvShowEntity) {
        if ( tvShowEntity == null ) {
            return null;
        }

        TvShowRestSeason tvShowRestSeason = new TvShowRestSeason();

        return tvShowRestSeason;
    }

    @Override
    public TvShowRestCategory mapToRestCategory(TvShowEntity tvShowEntity) {
        if ( tvShowEntity == null ) {
            return null;
        }

        TvShowRestCategory tvShowRestCategory = new TvShowRestCategory();

        tvShowRestCategory.setTvShowName( tvShowEntity.getTvShowName() );

        return tvShowRestCategory;
    }
}
