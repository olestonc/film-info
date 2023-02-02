package com.nttdata.bootcamp.mapper;

import com.nttdata.bootcamp.persistence.entity.ActorEntity;
import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;
import com.nttdata.bootcamp.service.responseModel.reponseActor.ActorResponseDTO;
import com.nttdata.bootcamp.service.responseModel.reponseActor.ActorWithChapetersResponseDTO;
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
public class ActorMapperImpl implements ActorMapper {

    @Override
    public ActorEntity mapResponseDTOToEntity(ActorResponseDTO rest) {
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
    public ActorResponseDTO mapEntityToResponseDTO(ActorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ActorResponseDTO actorResponseDTO = new ActorResponseDTO();

        if ( entity.getActorDescription() != null ) {
            actorResponseDTO.setActorDescription( Double.parseDouble( entity.getActorDescription() ) );
        }
        actorResponseDTO.setActorId( entity.getActorId() );
        actorResponseDTO.setActorName( entity.getActorName() );

        return actorResponseDTO;
    }

    @Override
    public ActorEntity mapWithChapetersResponseDTOToEntity(ActorWithChapetersResponseDTO rest) {
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
    public ActorWithChapetersResponseDTO mapEntityToWithChapetersResponseDTO(ActorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ActorWithChapetersResponseDTO actorWithChapetersResponseDTO = new ActorWithChapetersResponseDTO();

        Set<ChapeterEntity> set = entity.getActorChapeters();
        if ( set != null ) {
            actorWithChapetersResponseDTO.setActorChapeters( new HashSet<ChapeterEntity>( set ) );
        }
        if ( entity.getActorDescription() != null ) {
            actorWithChapetersResponseDTO.setActorDescription( Double.parseDouble( entity.getActorDescription() ) );
        }
        actorWithChapetersResponseDTO.setActorId( entity.getActorId() );
        actorWithChapetersResponseDTO.setActorName( entity.getActorName() );

        return actorWithChapetersResponseDTO;
    }
}
