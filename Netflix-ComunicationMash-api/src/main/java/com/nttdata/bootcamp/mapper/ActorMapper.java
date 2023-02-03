package com.nttdata.bootcamp.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.persistence.entity.ActorEntity;
import com.nttdata.bootcamp.service.responseModel.reponseActor.ActorResponseDTO;
import com.nttdata.bootcamp.service.responseModel.reponseActor.ActorWithChapetersResponseDTO;

@Component
@Mapper
public interface ActorMapper {

    ActorEntity mapResponseDTOToEntity(ActorResponseDTO rest);

    ActorResponseDTO mapEntityToResponseDTO(ActorEntity entity);

    ActorEntity mapWithChapetersResponseDTOToEntity(ActorWithChapetersResponseDTO rest);

    ActorWithChapetersResponseDTO mapEntityToWithChapetersResponseDTO(ActorEntity entity);

}
