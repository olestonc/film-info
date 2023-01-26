package com.example.demo.controller.rest;

import org.springframework.data.domain.Pageable;

import com.example.demo.controller.rest.model.ChapeterRest;
import com.example.demo.controller.rest.model.D4iPageRest;
import com.example.demo.controller.rest.model.NetflixResponse;
import com.example.demo.controller.rest.model.restChapeter.ChapeterRestPost;
import com.example.demo.exception.NetflixException;

public interface ChapeterControllerRest {

    NetflixResponse<D4iPageRest<ChapeterRest>> getAllChapeters(int page, int size, Pageable pageable) throws NetflixException;

    NetflixResponse<ChapeterRest> getChapeterById(Long id) throws NetflixException;

    NetflixResponse<ChapeterRestPost> createChapeter(ChapeterRestPost chapeter) throws NetflixException;

    NetflixResponse<ChapeterRestPost> updateChapeter(ChapeterRestPost chapeter) throws NetflixException;

    NetflixResponse<Object> deleteChapeter(Long id) throws NetflixException;


}
