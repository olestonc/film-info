package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.controller.rest.model.ChapeterRest;
import com.example.demo.controller.rest.model.restChapeter.ChapeterRestPost;
import com.example.demo.persistence.entity.ChapeterEntity;

@Mapper(componentModel = "spring")
public interface ChapeterMapper {

    ChapeterEntity mapToEntity(ChapeterRest rest);

    ChapeterRest mapToRest(ChapeterEntity entity);

    ChapeterEntity mapToEntity(ChapeterRestPost rest);

    ChapeterRestPost mapToRestPost(ChapeterEntity entity);
}
