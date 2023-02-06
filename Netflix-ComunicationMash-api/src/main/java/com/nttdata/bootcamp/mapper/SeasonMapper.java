package com.nttdata.bootcamp.mapper;

import org.mapstruct.Mapper;


import com.nttdata.bootcamp.persistence.entity.SeasonEntity;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonWithChapetersResponseDTO;

@Mapper(componentModel = "spring")
public interface SeasonMapper {

    SeasonEntity mapResponseDTOToEntity(SeasonResponseDTO rest);

    SeasonResponseDTO mapEntityToResponseDTO(SeasonEntity entity);

    SeasonEntity mapWithChapetersResponseDTOToEntity(SeasonWithChapetersResponseDTO rest);

    SeasonWithChapetersResponseDTO mapEntityToResponseWithChapetersDTO(SeasonEntity entity);
}
