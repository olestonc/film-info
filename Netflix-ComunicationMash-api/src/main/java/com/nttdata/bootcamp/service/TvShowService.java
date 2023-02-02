package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowWithCategoryDTO;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowWithSeasonDTO;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowResponseDTO;

public interface TvShowService {

    NetflixResponse<D4iPageRest<TvShowResponseDTO>> getAllTvShows(Pageable pageable);

    NetflixResponse<TvShowResponseDTO> getTvShowById(Long id);

    NetflixResponse<TvShowResponseDTO> createTvShow(TvShowResponseDTO tvshow);

    NetflixResponse<TvShowResponseDTO> updateTvShow(TvShowResponseDTO tvshow);

    NetflixResponse<TvShowResponseDTO> deleteTvShow(Long id);

    NetflixResponse<TvShowWithSeasonDTO> addSeasonOfTvShow(Long tvshowId, Long seasonId);

    NetflixResponse<TvShowResponseDTO> deleteSeasonOfTvShow(Long tvshowId, Long seasonId);

    NetflixResponse<TvShowWithCategoryDTO> setCategoryOfTvShow(Long tvshowId, Long categoryId);

    NetflixResponse<TvShowResponseDTO> deleteCategoryFromTvShow(Long tvshowId);

}
