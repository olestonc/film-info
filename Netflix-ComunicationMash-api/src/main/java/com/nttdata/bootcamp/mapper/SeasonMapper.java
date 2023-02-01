package com.nttdata.bootcamp.mapper;

import org.mapstruct.Mapper;

import com.nttdata.bootcamp.persistence.entity.SeasonEntity;
import com.nttdata.bootcamp.service.responseModel.SeasonRest;
import com.nttdata.bootcamp.service.responseModel.restSeason.SeasonRestPost;

@Mapper(componentModel = "spring")
public interface SeasonMapper {

    SeasonEntity mapToEntity(SeasonRest rest);

    SeasonRest mapToRest(SeasonEntity entity);

    SeasonEntity mapToEntity(SeasonRestPost rest);

    SeasonRestPost mapToRestPost(SeasonEntity entity);
}
