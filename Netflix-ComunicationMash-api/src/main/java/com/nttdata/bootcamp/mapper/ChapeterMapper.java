package com.nttdata.bootcamp.mapper;

import org.mapstruct.Mapper;

import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;
import com.nttdata.bootcamp.service.responseModel.ChapeterRest;
import com.nttdata.bootcamp.service.responseModel.restChapeter.ChapeterRestPost;



@Mapper(componentModel = "spring")
public interface ChapeterMapper {

    ChapeterEntity mapToEntity(ChapeterRest rest);

    ChapeterRest mapToRest(ChapeterEntity entity);

    ChapeterEntity mapToEntity(ChapeterRestPost rest);

    ChapeterRestPost mapToRestPost(ChapeterEntity entity);
}
