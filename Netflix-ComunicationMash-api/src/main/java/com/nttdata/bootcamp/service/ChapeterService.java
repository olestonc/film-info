package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterWithActorsResponseDTO;

public interface ChapeterService {
    NetflixResponse<D4iPageRest<ChapeterResponseDTO>> getAllChapeters(Pageable pageable);

    NetflixResponse<ChapeterResponseDTO> getChapeterById(Long id);

    NetflixResponse<ChapeterResponseDTO> createChapeter(ChapeterResponseDTO chapeter);

    NetflixResponse<ChapeterResponseDTO> updateChapeter(ChapeterResponseDTO chapeter);

    NetflixResponse<ChapeterResponseDTO> deleteChapeter(Long id);

    NetflixResponse<ChapeterResponseDTO> deleteActorFromChapeter(Long chapeterId, Long actorId);

    NetflixResponse<ChapeterWithActorsResponseDTO> addActorFromChapeter(Long chapeterId, Long actorId);

}
