package com.nttdata.bootcamp.mapper;

import org.mapstruct.Mapper;


import com.nttdata.bootcamp.persistence.entity.TvShowEntity;
import com.nttdata.bootcamp.service.responseModel.TvShowRest;
import com.nttdata.bootcamp.service.responseModel.restTvShow.TvShowRestCategory;
import com.nttdata.bootcamp.service.responseModel.restTvShow.TvShowRestPost;
import com.nttdata.bootcamp.service.responseModel.restTvShow.TvShowRestSeason;
import com.nttdata.bootcamp.service.responseModel.restTvShow.TvShowRestShort;

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
