package com.nttdata.bootcamp.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.nttdata.bootcamp.exception.NetflixException;
import com.nttdata.bootcamp.service.responseModel.ChapeterRest;
import com.nttdata.bootcamp.service.responseModel.restChapeter.ChapeterRestPost;

public interface ChapeterService {
    Page<ChapeterRest> getAllChapeters(Pageable pageable) throws NetflixException;

    ChapeterRest getChapeterById(Long id) throws NetflixException;

    ChapeterRestPost createChapeter(ChapeterRestPost chapeter) throws NetflixException;

    ChapeterRestPost updateChapeter(ChapeterRestPost chapeter) throws NetflixException;

    void deleteChapeter(Long id) throws NetflixException;

}
