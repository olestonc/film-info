package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.controller.rest.model.TvShowRest;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestPost;
import com.example.demo.persistence.entity.TvShowEntity;

@Mapper(componentModel = "spring")
public interface TvShowMapper {

    TvShowEntity mapToEntity(TvShowRest rest);

    TvShowRest mapToRest(TvShowEntity entity);

    TvShowEntity mapToEntity(TvShowRestPost rest);

    TvShowRestPost mapToRestPost(TvShowEntity entity);
}
