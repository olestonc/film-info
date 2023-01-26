package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.controller.rest.model.SeasonRest;
import com.example.demo.controller.rest.model.restSeason.SeasonRestPost;
import com.example.demo.persistence.entity.SeasonEntity;

@Mapper(componentModel = "spring")
public interface SeasonMapper {

    SeasonEntity mapToEntity(SeasonRest rest);

    SeasonRest mapToRest(SeasonEntity entity);

    SeasonEntity mapToEntity(SeasonRestPost rest);

    SeasonRestPost mapToRestPost(SeasonEntity entity);
}
