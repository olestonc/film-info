package com.example.demo.controller.rest;

import org.springframework.data.domain.Pageable;

import com.example.demo.controller.rest.model.ActorRest;
import com.example.demo.controller.rest.model.D4iPageRest;
import com.example.demo.controller.rest.model.NetflixResponse;
import com.example.demo.controller.rest.model.restActor.ActorRestPost;
import com.example.demo.exception.NetflixException;

public interface ActorControllerRest {

    NetflixResponse<D4iPageRest<ActorRest>> getAllActors(int page, int size, Pageable pageable) throws NetflixException;

    NetflixResponse<ActorRest> getActorById(Long id) throws NetflixException;

    NetflixResponse<ActorRestPost> createActor(ActorRestPost actor) throws NetflixException;

    NetflixResponse<ActorRestPost> updateActor(ActorRestPost actor) throws NetflixException;

    NetflixResponse<Object> deleteActor(Long id) throws NetflixException;


}
