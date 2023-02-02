package com.nttdata.bootcamp.mapper;

import com.nttdata.bootcamp.persistence.entity.TvShowEntity;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowWithCategoryDTO;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowWithSeasonDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-02T17:14:50+0100",
    comments = "version: 1.4.0.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20221215-1352, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class TvShowMapperImpl implements TvShowMapper {

    @Override
    public TvShowResponseDTO mapEntityToResponseDTO(TvShowEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TvShowResponseDTO tvShowResponseDTO = new TvShowResponseDTO();

        tvShowResponseDTO.setTvShowAdvertising( entity.isTvShowAdvertising() );
        tvShowResponseDTO.setTvShowId( entity.getTvShowId() );
        tvShowResponseDTO.setTvShowLongDescription( entity.getTvShowLongDescription() );
        tvShowResponseDTO.setTvShowName( entity.getTvShowName() );
        tvShowResponseDTO.setTvShowRecommendedAge( entity.getTvShowRecommendedAge() );
        tvShowResponseDTO.setTvShowShortDescription( entity.getTvShowShortDescription() );
        tvShowResponseDTO.setTvShowYear( entity.getTvShowYear() );

        return tvShowResponseDTO;
    }

    @Override
    public TvShowWithCategoryDTO mapEntityToResponseDTOWithCategory(TvShowEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TvShowWithCategoryDTO tvShowWithCategoryDTO = new TvShowWithCategoryDTO();

        tvShowWithCategoryDTO.setTvShowId( entity.getTvShowId() );
        tvShowWithCategoryDTO.setTvShowName( entity.getTvShowName() );

        return tvShowWithCategoryDTO;
    }

    @Override
    public TvShowWithSeasonDTO mapEntityToResponseDTOWithSeason(TvShowEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TvShowWithSeasonDTO tvShowWithSeasonDTO = new TvShowWithSeasonDTO();

        tvShowWithSeasonDTO.setTvShowId( entity.getTvShowId() );
        tvShowWithSeasonDTO.setTvShowName( entity.getTvShowName() );

        return tvShowWithSeasonDTO;
    }

    @Override
    public TvShowEntity mapResponseDTOToEntity(TvShowResponseDTO tvshow) {
        if ( tvshow == null ) {
            return null;
        }

        TvShowEntity tvShowEntity = new TvShowEntity();

        tvShowEntity.setTvShowAdvertising( tvshow.isTvShowAdvertising() );
        tvShowEntity.setTvShowId( tvshow.getTvShowId() );
        tvShowEntity.setTvShowLongDescription( tvshow.getTvShowLongDescription() );
        tvShowEntity.setTvShowName( tvshow.getTvShowName() );
        tvShowEntity.setTvShowRecommendedAge( tvshow.getTvShowRecommendedAge() );
        tvShowEntity.setTvShowShortDescription( tvshow.getTvShowShortDescription() );
        tvShowEntity.setTvShowYear( tvshow.getTvShowYear() );

        return tvShowEntity;
    }
}
