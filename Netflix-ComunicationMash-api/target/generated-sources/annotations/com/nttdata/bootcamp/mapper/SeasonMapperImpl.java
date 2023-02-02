package com.nttdata.bootcamp.mapper;

import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;
import com.nttdata.bootcamp.persistence.entity.SeasonEntity;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonWithChapetersResponseDTO;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-02T14:21:41+0100",
    comments = "version: 1.4.0.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20221215-1352, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class SeasonMapperImpl implements SeasonMapper {

    @Override
    public SeasonEntity mapResponseDTOToEntity(SeasonResponseDTO rest) {
        if ( rest == null ) {
            return null;
        }

        SeasonEntity seasonEntity = new SeasonEntity();

        seasonEntity.setSeasonId( rest.getSeasonId() );
        seasonEntity.setSeasonName( rest.getSeasonName() );
        seasonEntity.setSeasonNumber( rest.getSeasonNumber() );

        return seasonEntity;
    }

    @Override
    public SeasonResponseDTO mapEntityToResponseDTO(SeasonEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SeasonResponseDTO seasonResponseDTO = new SeasonResponseDTO();

        seasonResponseDTO.setSeasonId( entity.getSeasonId() );
        seasonResponseDTO.setSeasonName( entity.getSeasonName() );
        seasonResponseDTO.setSeasonNumber( entity.getSeasonNumber() );

        return seasonResponseDTO;
    }

    @Override
    public SeasonEntity mapWithChapetersResponseDTOToEntity(SeasonWithChapetersResponseDTO rest) {
        if ( rest == null ) {
            return null;
        }

        SeasonEntity seasonEntity = new SeasonEntity();

        Set<ChapeterEntity> set = rest.getSeasonChapeters();
        if ( set != null ) {
            seasonEntity.setSeasonChapeters( new HashSet<ChapeterEntity>( set ) );
        }
        seasonEntity.setSeasonId( rest.getSeasonId() );
        seasonEntity.setSeasonName( rest.getSeasonName() );
        seasonEntity.setSeasonNumber( rest.getSeasonNumber() );

        return seasonEntity;
    }

    @Override
    public SeasonWithChapetersResponseDTO mapEntityToResponseWithChapetersDTO(SeasonEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SeasonWithChapetersResponseDTO seasonWithChapetersResponseDTO = new SeasonWithChapetersResponseDTO();

        Set<ChapeterEntity> set = entity.getSeasonChapeters();
        if ( set != null ) {
            seasonWithChapetersResponseDTO.setSeasonChapeters( new HashSet<ChapeterEntity>( set ) );
        }
        seasonWithChapetersResponseDTO.setSeasonId( entity.getSeasonId() );
        seasonWithChapetersResponseDTO.setSeasonName( entity.getSeasonName() );
        seasonWithChapetersResponseDTO.setSeasonNumber( entity.getSeasonNumber() );

        return seasonWithChapetersResponseDTO;
    }
}
