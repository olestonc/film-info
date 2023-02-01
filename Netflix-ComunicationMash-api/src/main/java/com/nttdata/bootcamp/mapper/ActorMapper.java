package com.nttdata.bootcamp.mapper;

import org.mapstruct.Mapper;

import com.nttdata.bootcamp.persistence.entity.ActorEntity;
import com.nttdata.bootcamp.service.responseModel.restActor.ActorRequestDTO;
import com.nttdata.bootcamp.service.responseModel.restActor.ActorWithChapetersRequestDTO;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorEntity mapRequestDTOToEntity(ActorRequestDTO rest);

    ActorRequestDTO mapEntityToRequestDTO(ActorEntity entity);

    ActorEntity mapWithChapetersRequestDTOToEntity(ActorWithChapetersRequestDTO rest);

    ActorWithChapetersRequestDTO mapEntityToWithChapetersRequestDTO(ActorEntity entity);

}
