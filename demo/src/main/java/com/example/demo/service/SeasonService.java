package com.example.demo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.example.demo.controller.rest.model.SeasonRest;
import com.example.demo.controller.rest.model.restSeason.SeasonRestPost;
import com.example.demo.exception.NetflixException;

public interface SeasonService {
    Page<SeasonRest> getAllSeasons(Pageable pageable) throws NetflixException;

    SeasonRest getSeasonById(Long id) throws NetflixException;

    SeasonRestPost createSeason(SeasonRestPost season) throws NetflixException;

    SeasonRestPost updateSeason(SeasonRestPost season) throws NetflixException;

    void deleteSeason(Long id) throws NetflixException;

}
