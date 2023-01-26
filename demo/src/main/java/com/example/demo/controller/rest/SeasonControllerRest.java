package com.example.demo.controller.rest;

import org.springframework.data.domain.Pageable;

import com.example.demo.controller.rest.model.SeasonRest;
import com.example.demo.controller.rest.model.restSeason.SeasonRestPost;
import com.example.demo.exception.NetflixException;
import com.example.demo.controller.rest.model.D4iPageRest;
import com.example.demo.controller.rest.model.NetflixResponse;

public interface SeasonControllerRest {

    NetflixResponse<D4iPageRest<SeasonRest>> getAllSeasons(int page, int size, Pageable pageable) throws NetflixException;

    NetflixResponse<SeasonRest> getSeasonById(Long id) throws NetflixException;

    NetflixResponse<SeasonRestPost> createSeason(SeasonRestPost season) throws NetflixException;

    NetflixResponse<SeasonRestPost> updateSeason(SeasonRestPost season) throws NetflixException;

   void deleteSeason(Long id) throws NetflixException;


}
