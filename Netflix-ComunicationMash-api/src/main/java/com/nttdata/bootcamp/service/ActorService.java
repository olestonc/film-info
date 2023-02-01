package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.restActor.ActorRequestDTO;
import com.nttdata.bootcamp.service.responseModel.restActor.ActorWithChapetersRequestDTO;

public interface ActorService {
    NetflixResponse<D4iPageRest<ActorRequestDTO>> getAllActors(Pageable pageable);

    NetflixResponse<ActorRequestDTO> getActorById(Long id);

    NetflixResponse<ActorRequestDTO> createActor(ActorRequestDTO actor);

    NetflixResponse<ActorRequestDTO> updateActor(ActorRequestDTO actor);

    void deleteActor(Long id);

    NetflixResponse<ActorWithChapetersRequestDTO> getActorWithChapetersById(Long id);
}
