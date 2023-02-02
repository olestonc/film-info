package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.reponseActor.ActorResponseDTO;
import com.nttdata.bootcamp.service.responseModel.reponseActor.ActorWithChapetersResponseDTO;

public interface ActorService {
    NetflixResponse<D4iPageRest<ActorResponseDTO>> getAllActors(Pageable pageable);

    NetflixResponse<ActorResponseDTO> getActorById(Long id);

    NetflixResponse<ActorResponseDTO> createActor(ActorResponseDTO actor);

    NetflixResponse<ActorResponseDTO> updateActor(ActorResponseDTO actor);

    NetflixResponse<ActorResponseDTO> deleteActor(Long id);

    NetflixResponse<ActorWithChapetersResponseDTO> getActorWithChapetersById(Long id);
}
