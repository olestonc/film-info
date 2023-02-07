package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.reponseActor.ActorResponseDTO;
import com.nttdata.bootcamp.service.responseModel.reponseActor.ActorWithChapetersResponseDTO;

public interface ActorService {
    D4iPageRest<ActorResponseDTO> getAllActors(Pageable pageable);

    ActorResponseDTO getActorById(Long id) throws NetflixNotFoundException;

    ActorResponseDTO createActor(ActorResponseDTO actor);

    ActorResponseDTO updateActor(ActorResponseDTO actor) throws NetflixNotFoundException;

    void deleteActor(Long id);

    ActorWithChapetersResponseDTO getActorWithChapetersById(Long id) throws NetflixNotFoundException;
}
