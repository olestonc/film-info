package com.nttdata.bootcamp.mapper;

import com.nttdata.bootcamp.persistence.entity.ActorEntity;
import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;
import com.nttdata.bootcamp.service.responseModel.restActor.ActorRequestDTO;
import com.nttdata.bootcamp.service.responseModel.restActor.ActorWithChapetersRequestDTO;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-01T16:40:34+0100",
    comments = "version: 1.4.0.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20221215-1352, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class ActorMapperImpl implements ActorMapper {

    @Override
    public ActorEntity mapRequestDTOToEntity(ActorRequestDTO rest) {
        if ( rest == null ) {
            return null;
        }

        ActorEntity actorEntity = new ActorEntity();

        if ( rest.getActorDescription() != null ) {
            actorEntity.setActorDescription( String.valueOf( rest.getActorDescription() ) );
        }
        actorEntity.setActorId( rest.getActorId() );
        actorEntity.setActorName( rest.getActorName() );

        return actorEntity;
    }

    @Override
    public ActorRequestDTO mapEntityToRequestDTO(ActorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ActorRequestDTO actorRequestDTO = new ActorRequestDTO();

        if ( entity.getActorDescription() != null ) {
            actorRequestDTO.setActorDescription( Double.parseDouble( entity.getActorDescription() ) );
        }
        actorRequestDTO.setActorId( entity.getActorId() );
        actorRequestDTO.setActorName( entity.getActorName() );

        return actorRequestDTO;
    }

    @Override
    public ActorEntity mapWithChapetersRequestDTOToEntity(ActorWithChapetersRequestDTO rest) {
        if ( rest == null ) {
            return null;
        }

        ActorEntity actorEntity = new ActorEntity();

        Set<ChapeterEntity> set = rest.getActorChapeters();
        if ( set != null ) {
            actorEntity.setActorChapeters( new HashSet<ChapeterEntity>( set ) );
        }
        if ( rest.getActorDescription() != null ) {
            actorEntity.setActorDescription( String.valueOf( rest.getActorDescription() ) );
        }
        actorEntity.setActorId( rest.getActorId() );
        actorEntity.setActorName( rest.getActorName() );

        return actorEntity;
    }

    @Override
    public ActorWithChapetersRequestDTO mapEntityToWithChapetersRequestDTO(ActorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ActorWithChapetersRequestDTO actorWithChapetersRequestDTO = new ActorWithChapetersRequestDTO();

        Set<ChapeterEntity> set = entity.getActorChapeters();
        if ( set != null ) {
            actorWithChapetersRequestDTO.setActorChapeters( new HashSet<ChapeterEntity>( set ) );
        }
        if ( entity.getActorDescription() != null ) {
            actorWithChapetersRequestDTO.setActorDescription( Double.parseDouble( entity.getActorDescription() ) );
        }
        actorWithChapetersRequestDTO.setActorId( entity.getActorId() );
        actorWithChapetersRequestDTO.setActorName( entity.getActorName() );

        return actorWithChapetersRequestDTO;
    }
}
