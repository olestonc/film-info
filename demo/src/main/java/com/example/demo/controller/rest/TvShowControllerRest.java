package com.example.demo.controller.rest;

import org.springframework.data.domain.Pageable;

import com.example.demo.controller.rest.model.TvShowRest;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestPost;
import com.example.demo.controller.rest.model.D4iPageRest;
import com.example.demo.controller.rest.model.NetflixResponse;
import com.example.demo.exception.NetflixException;

public interface TvShowControllerRest {

    NetflixResponse<D4iPageRest<TvShowRest>> getAllTvShows(int page, int size, Pageable pageable) throws NetflixException;

    NetflixResponse<TvShowRest> getTvShowById(Long id) throws NetflixException;

    NetflixResponse<TvShowRestPost> createTvShow(TvShowRestPost tvshow) throws NetflixException;

    NetflixResponse<TvShowRestPost> updateTvShow(TvShowRestPost tvshow) throws NetflixException;

    NetflixResponse<Object> deleteTvShow(Long id) throws NetflixException;


}
