package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.persistence.entity.ActorEntity;

public interface ActorService {
    List<ActorEntity> getAllActors();

    Optional<ActorEntity> getActorById(Long id);

    ActorEntity save(ActorEntity Actor);

    void deleteActor(Long id);

}
