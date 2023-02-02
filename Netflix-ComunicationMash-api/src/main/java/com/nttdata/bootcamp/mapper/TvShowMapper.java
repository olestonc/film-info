package com.nttdata.bootcamp.mapper;

import org.mapstruct.Mapper;

import com.nttdata.bootcamp.persistence.entity.TvShowEntity;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowWithCategoryDTO;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowWithSeasonDTO;

@Mapper(componentModel = "spring")
public interface TvShowMapper {

    TvShowResponseDTO mapEntityToResponseDTO(TvShowEntity entity);

    TvShowWithCategoryDTO mapEntityToResponseDTOWithCategory(TvShowEntity entity);

    TvShowWithSeasonDTO mapEntityToResponseDTOWithSeason(TvShowEntity entity);

    TvShowEntity mapResponseDTOToEntity(TvShowResponseDTO tvshow);

}
