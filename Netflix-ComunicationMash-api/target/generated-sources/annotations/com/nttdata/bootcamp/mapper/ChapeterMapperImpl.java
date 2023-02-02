package com.nttdata.bootcamp.mapper;

import com.nttdata.bootcamp.persistence.entity.ActorEntity;
import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterWithActorsResponseDTO;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-02T16:17:59+0100",
    comments = "version: 1.4.0.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20221215-1352, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class ChapeterMapperImpl implements ChapeterMapper {

    @Override
    public ChapeterEntity mapResponseDTOToEntity(ChapeterResponseDTO rest) {
        if ( rest == null ) {
            return null;
        }

        ChapeterEntity chapeterEntity = new ChapeterEntity();

        if ( rest.getChapeterDuration() != null ) {
            chapeterEntity.setChapeterDuration( Integer.parseInt( rest.getChapeterDuration() ) );
        }
        chapeterEntity.setChapeterId( rest.getChapeterId() );
        chapeterEntity.setChapeterName( rest.getChapeterName() );
        chapeterEntity.setChapeterNumber( rest.getChapeterNumber() );

        return chapeterEntity;
    }

    @Override
    public ChapeterResponseDTO mapEntityToResponseDTO(ChapeterEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ChapeterResponseDTO chapeterResponseDTO = new ChapeterResponseDTO();

        chapeterResponseDTO.setChapeterDuration( String.valueOf( entity.getChapeterDuration() ) );
        chapeterResponseDTO.setChapeterId( entity.getChapeterId() );
        chapeterResponseDTO.setChapeterName( entity.getChapeterName() );
        chapeterResponseDTO.setChapeterNumber( entity.getChapeterNumber() );

        return chapeterResponseDTO;
    }

    @Override
    public ChapeterWithActorsResponseDTO mapEntityToWithActorsResponseDTO(ChapeterEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ChapeterWithActorsResponseDTO chapeterWithActorsResponseDTO = new ChapeterWithActorsResponseDTO();

        Set<ActorEntity> set = entity.getChapeterActors();
        if ( set != null ) {
            chapeterWithActorsResponseDTO.setChapeterActors( new HashSet<ActorEntity>( set ) );
        }
        chapeterWithActorsResponseDTO.setChapeterDuration( String.valueOf( entity.getChapeterDuration() ) );
        chapeterWithActorsResponseDTO.setChapeterId( entity.getChapeterId() );
        chapeterWithActorsResponseDTO.setChapeterName( entity.getChapeterName() );
        chapeterWithActorsResponseDTO.setChapeterNumber( entity.getChapeterNumber() );

        return chapeterWithActorsResponseDTO;
    }

    @Override
    public ChapeterEntity mapWithActorsResponseDTOToEntity(ChapeterWithActorsResponseDTO rest) {
        if ( rest == null ) {
            return null;
        }

        ChapeterEntity chapeterEntity = new ChapeterEntity();

        Set<ActorEntity> set = rest.getChapeterActors();
        if ( set != null ) {
            chapeterEntity.setChapeterActors( new HashSet<ActorEntity>( set ) );
        }
        if ( rest.getChapeterDuration() != null ) {
            chapeterEntity.setChapeterDuration( Integer.parseInt( rest.getChapeterDuration() ) );
        }
        chapeterEntity.setChapeterId( rest.getChapeterId() );
        chapeterEntity.setChapeterName( rest.getChapeterName() );
        chapeterEntity.setChapeterNumber( rest.getChapeterNumber() );

        return chapeterEntity;
    }
}
