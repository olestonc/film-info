package com.nttdata.bootcamp.mapper;

import org.mapstruct.Mapper;

import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterWithActorsResponseDTO;

@Mapper(componentModel = "spring")
public interface ChapeterMapper {

    ChapeterEntity mapResponseDTOToEntity(ChapeterResponseDTO rest);

    ChapeterResponseDTO mapEntityToResponseDTO(ChapeterEntity entity);

    ChapeterWithActorsResponseDTO mapEntityToWithActorsResponseDTO(ChapeterEntity entity);

    ChapeterEntity mapWithActorsResponseDTOToEntity(ChapeterWithActorsResponseDTO rest);
}
