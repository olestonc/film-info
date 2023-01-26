package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.persistence.entity.TvShowEntity;

public interface TvShowService {
    List<TvShowEntity> getAllTvShows();

    Optional<TvShowEntity> getTvShowById(Long id);

    TvShowEntity save(TvShowEntity TvShow);

    void deleteTvShow(Long id);

}
