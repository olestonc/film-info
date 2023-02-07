package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterWithActorsResponseDTO;

public interface ChapeterService {
    D4iPageRest<ChapeterResponseDTO> getAllChapeters(Pageable pageable);

    ChapeterResponseDTO getChapeterById(Long id) throws NetflixNotFoundException;

    ChapeterResponseDTO createChapeter(ChapeterResponseDTO chapeter);

    ChapeterResponseDTO updateChapeter(ChapeterResponseDTO chapeter) throws NetflixNotFoundException;

    void deleteChapeter(Long id);

    void deleteActorFromChapeter(Long chapeterId, Long actorId) throws NetflixNotFoundException;

    ChapeterWithActorsResponseDTO addActorFromChapeter(Long chapeterId, Long actorId) throws NetflixNotFoundException;

}
