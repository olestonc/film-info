package com.example.demo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.example.demo.controller.rest.model.ActorRest;
import com.example.demo.controller.rest.model.restActor.ActorRestPost;
import com.example.demo.exception.NetflixException;

public interface ActorService {
    Page<ActorRest> getAllActors(Pageable pageable) throws NetflixException;

    ActorRest getActorById(Long id) throws NetflixException;

    ActorRestPost createActor(ActorRestPost actor) throws NetflixException;

    ActorRestPost updateActor(ActorRestPost actor) throws NetflixException;

    void deleteActor(Long id) throws NetflixException;
}
