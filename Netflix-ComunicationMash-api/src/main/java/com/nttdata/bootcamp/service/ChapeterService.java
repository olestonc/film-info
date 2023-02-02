package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;

import com.nttdata.bootcamp.service.responseModel.ChapeterRest;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterResponseDTO;

public interface ChapeterService {
    NetflixResponse<D4iPageRest<ChapeterRest>> getAllChapeters(Pageable pageable);

    NetflixResponse<ChapeterRest> getChapeterById(Long id);

    NetflixResponse<ChapeterResponseDTO> createChapeter(ChapeterResponseDTO chapeter);

    NetflixResponse<ChapeterResponseDTO> updateChapeter(ChapeterResponseDTO chapeter);

    void deleteChapeter(Long id);

}
