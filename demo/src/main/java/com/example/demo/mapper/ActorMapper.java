package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.controller.rest.model.ActorRest;
import com.example.demo.controller.rest.model.restActor.ActorRestPost;
import com.example.demo.persistence.entity.ActorEntity;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorEntity mapToEntity(ActorRest rest);

    ActorRest mapToRest(ActorEntity entity);

    ActorEntity mapToEntity(ActorRestPost rest);

    ActorRestPost mapToRestPost(ActorEntity entity);
}
