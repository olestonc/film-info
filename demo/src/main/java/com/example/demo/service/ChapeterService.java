package com.example.demo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.example.demo.controller.rest.model.ChapeterRest;
import com.example.demo.controller.rest.model.restChapeter.ChapeterRestPost;
import com.example.demo.exception.NetflixException;

public interface ChapeterService {
    Page<ChapeterRest> getAllChapeters(Pageable pageable) throws NetflixException;

    ChapeterRest getChapeterById(Long id) throws NetflixException;

    ChapeterRestPost createChapeter(ChapeterRestPost chapeter) throws NetflixException;

    ChapeterRestPost updateChapeter(ChapeterRestPost chapeter) throws NetflixException;

    void deleteChapeter(Long id) throws NetflixException;

}
