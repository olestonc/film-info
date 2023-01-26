package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.controller.rest.model.TvShowRest;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestCategory;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestPost;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestSeason;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestShort;
import com.example.demo.persistence.entity.TvShowEntity;

@Mapper(componentModel = "spring")
public interface TvShowMapper {

    TvShowEntity mapToEntity(TvShowRest rest);
    TvShowRest mapToRest(TvShowEntity entity);
    
    TvShowEntity mapToEntity(TvShowRestPost rest);
    TvShowRestPost mapToRestPost(TvShowEntity entity);
    
    TvShowEntity mapToEntity(TvShowRestShort rest);
    TvShowRestShort mapToRestShort(TvShowEntity entity);
    
    TvShowRestSeason mapToRestSeason(TvShowEntity tvShowEntity);
    TvShowRestCategory mapToRestCategory(TvShowEntity tvShowEntity);

}
