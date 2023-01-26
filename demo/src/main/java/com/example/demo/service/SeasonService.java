package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.persistence.entity.SeasonEntity;

public interface SeasonService {
    List<SeasonEntity> getAllSeasons();

    Optional<SeasonEntity> getSeasonById(Long id);

    SeasonEntity save(SeasonEntity Season);

    void deleteSeason(Long id);

}
