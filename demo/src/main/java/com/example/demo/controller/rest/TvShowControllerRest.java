package com.example.demo.controller.rest;

import org.springframework.data.domain.Pageable;

import com.example.demo.controller.rest.model.restTvShow.TvShowRestCategory;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestSeason;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestShort;
import com.example.demo.controller.rest.model.D4iPageRest;
import com.example.demo.controller.rest.model.NetflixResponse;
import com.example.demo.exception.NetflixException;

public interface TvShowControllerRest {

    NetflixResponse<D4iPageRest<TvShowRestShort>> getAllTvShows(int page, int size, Pageable pageable)
            throws NetflixException;

    NetflixResponse<TvShowRestShort> getTvShowById(Long id) throws NetflixException;

    NetflixResponse<TvShowRestShort> createTvShow(TvShowRestShort tvshow) throws NetflixException;

    NetflixResponse<TvShowRestShort> updateTvShow(TvShowRestShort tvshow) throws NetflixException;

    NetflixResponse<TvShowRestCategory> addCategoryToTvShow(Long idTvShow, Long category) throws NetflixException;

    NetflixResponse<TvShowRestSeason> addSeasonToTvShow(Long idTvShow, Long season) throws NetflixException;

    void deleteTvShow(Long idTvShow) throws NetflixException;

    void deleteCategoryOnTvShow(Long idTvShow) throws NetflixException;

    void deleteSeasonOnTvShow(Long idTvShow, Long season) throws NetflixException;

}
