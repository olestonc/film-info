package com.example.demo.exception;

import java.util.Collection;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.error.ErrorDto;

import lombok.Getter;

@Getter
public class NetflixNotFoundException extends NetflixException {

    private static final long serialVersionUID = 1419856382856533644L;

    public NetflixNotFoundException(final ErrorDto errorDto) {
        super(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), errorDto);
    }

    public NetflixNotFoundException(final Collection<ErrorDto> errorDtoCollection) {
        super(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), errorDtoCollection);
    }

}
