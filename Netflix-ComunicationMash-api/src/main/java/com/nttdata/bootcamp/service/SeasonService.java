package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonWithChapetersResponseDTO;

public interface SeasonService {
    D4iPageRest<SeasonResponseDTO> getAllSeasons(Pageable pageable);

    SeasonResponseDTO getSeasonById(Long id) throws NetflixNotFoundException;

    void createSeason(SeasonResponseDTO season);

    SeasonResponseDTO updateSeason(SeasonResponseDTO season) throws NetflixNotFoundException;

    void deleteSeason(Long id);

    void deleteChapeterFromSeason(Long seasonId, Long chapeterId) throws NetflixNotFoundException;

    SeasonWithChapetersResponseDTO addChapeterFromSeason(Long seasonId, Long chapeterId) throws NetflixNotFoundException;

}
