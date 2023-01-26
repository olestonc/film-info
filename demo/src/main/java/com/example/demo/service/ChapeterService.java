package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.persistence.entity.ChapeterEntity;

public interface ChapeterService {
    List<ChapeterEntity> getAllChapeters();

    Optional<ChapeterEntity> getChapeterById(Long id);

    ChapeterEntity save(ChapeterEntity Chapeter);

    void deleteChapeter(Long id);

}
