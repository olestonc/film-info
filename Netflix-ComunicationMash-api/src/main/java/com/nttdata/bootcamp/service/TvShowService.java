package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowWithCategoryDTO;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowWithSeasonDTO;
import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowResponseDTO;

public interface TvShowService {

    D4iPageRest<TvShowResponseDTO> getAllTvShows(Pageable pageable);

    TvShowResponseDTO getTvShowById(Long id) throws NetflixNotFoundException;

    TvShowResponseDTO createTvShow(TvShowResponseDTO tvshow);

    TvShowResponseDTO updateTvShow(TvShowResponseDTO tvshow) throws NetflixNotFoundException;

    void deleteTvShow(Long id);

    TvShowWithSeasonDTO addSeasonOfTvShow(Long tvshowId, Long seasonId) throws NetflixNotFoundException;

    void deleteSeasonOfTvShow(Long tvshowId, Long seasonId) throws NetflixNotFoundException;

    TvShowWithCategoryDTO setCategoryOfTvShow(Long tvshowId, Long categoryId) throws NetflixNotFoundException;

    void deleteCategoryFromTvShow(Long tvshowId) throws NetflixNotFoundException;

}
