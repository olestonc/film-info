package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.exception.NetflixException;
import com.nttdata.bootcamp.service.responseModel.SeasonRest;
import com.nttdata.bootcamp.service.responseModel.restSeason.SeasonRestPost;

public interface SeasonService {
    Page<SeasonRest> getAllSeasons(Pageable pageable) throws NetflixException;

    SeasonRest getSeasonById(Long id) throws NetflixException;

    SeasonRestPost createSeason(SeasonRestPost season) throws NetflixException;

    SeasonRestPost updateSeason(SeasonRestPost season) throws NetflixException;

    void deleteSeason(Long id) throws NetflixException;

}
