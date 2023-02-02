package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonWithChapetersResponseDTO;

public interface SeasonService {
    NetflixResponse<D4iPageRest<SeasonResponseDTO>> getAllSeasons(Pageable pageable);

    NetflixResponse<SeasonResponseDTO> getSeasonById(Long id);

    NetflixResponse<SeasonResponseDTO> createSeason(SeasonResponseDTO season);

    NetflixResponse<SeasonResponseDTO> updateSeason(SeasonResponseDTO season);

    NetflixResponse<SeasonResponseDTO> deleteSeason(Long id);

    NetflixResponse<SeasonResponseDTO> deleteChapeterFromSeason(Long seasonId, Long chapeterId);

    NetflixResponse<SeasonWithChapetersResponseDTO> addChapeterFromSeason(Long seasonId, Long chapeterId);

}
