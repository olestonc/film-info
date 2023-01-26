package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.controller.rest.model.restTvShow.TvShowRestCategory;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestSeason;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestShort;
import com.example.demo.exception.NetflixException;

public interface TvShowService {
    Page<TvShowRestShort> getAllTvShows(Pageable pageable) throws NetflixException;

    TvShowRestShort getTvShowById(Long id) throws NetflixException;

    TvShowRestShort createTvShow(TvShowRestShort tvshow) throws NetflixException;

    TvShowRestShort updateTvShow(TvShowRestShort tvshow) throws NetflixException;

    TvShowRestSeason addSeasonOfTvShow(Long tvshowId, Long seasonId)  throws NetflixException;

    TvShowRestCategory addCategoryOfTvShow(Long tvshowId, Long categoryId) throws NetflixException;
    
    void deleteTvShow(Long id) throws NetflixException;

    void deleteSeasonOfTvShow(Long tvshowId, Long seasonId) throws NetflixException;

    void deleteCategoryOfTvShow(Long tvshowId) throws NetflixException;

}
