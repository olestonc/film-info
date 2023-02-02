package com.nttdata.bootcamp.mapper;

import org.mapstruct.Mapper;


import com.nttdata.bootcamp.persistence.entity.TvShowEntity;
import com.nttdata.bootcamp.service.responseModel.TvShowRest;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowRestCategory;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowRestPost;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowRestSeason;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowRestShort;

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
