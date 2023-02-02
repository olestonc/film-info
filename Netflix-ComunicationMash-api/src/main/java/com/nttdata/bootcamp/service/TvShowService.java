package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.nttdata.bootcamp.exception.NetflixException;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowRestCategory;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowRestSeason;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowRestShort;

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
