package com.example.demo.exception;

import java.util.Collection;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.error.ErrorDto;


public class NetflixBadRequestException extends NetflixException {

    private static final long serialVersionUID = 105837498733124083L;

    public NetflixBadRequestException(final Collection<ErrorDto> errorDtoCollection) {
        super(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), errorDtoCollection);
    }

    public NetflixBadRequestException(final ErrorDto errorDto) {
        super(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), errorDto);
    }

}
