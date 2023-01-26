package com.example.demo.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.rest.model.ChapeterRest;
import com.example.demo.controller.rest.model.restChapeter.ChapeterRestPost;
import com.example.demo.exception.NetflixException;
import com.example.demo.exception.NetflixNotFoundException;
import com.example.demo.exception.error.ErrorDto;
import com.example.demo.mapper.ChapeterMapper;
import com.example.demo.persistence.entity.ChapeterEntity;
import com.example.demo.persistence.repository.ChapeterRepository;
import com.example.demo.service.ChapeterService;
import com.example.demo.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChapeterServiceImpl implements ChapeterService {
    private final ChapeterRepository chapeterRepository;

    private final ChapeterMapper chapeterMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ChapeterRest> getAllChapeters(Pageable pageable) throws NetflixException {
        Page<ChapeterEntity> page = chapeterRepository.findAll(pageable);
        return page.map(chapeterMapper::mapToRest);
    }

    @Override
    @Transactional(readOnly = true)
    public ChapeterRest getChapeterById(Long id) throws NetflixException {
        /*
         * Equivale a usar la clase optional para recibir de repositorio y
         * if(optional.get()==null) then throws Not Found
         */
        ChapeterEntity chapeter = chapeterRepository.findById(id)
                .orElseThrow(
                        () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return chapeterMapper.mapToRest(chapeter);
    }

    @Override
    @Transactional
    public ChapeterRestPost createChapeter(ChapeterRestPost chapeterRest) throws NetflixException {
        ChapeterEntity chapeterEntity = chapeterMapper.mapToEntity(chapeterRest);
        chapeterRepository.save(chapeterEntity);// Donde se valida que un chapeter no tiene datos inválidos?
        return chapeterRest;
    }

    @Override
    @Transactional
    public ChapeterRestPost updateChapeter(ChapeterRestPost chapeterRest) throws NetflixException {
        ChapeterEntity chapeterOld = chapeterRepository.findById(chapeterRest.getId()).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        ChapeterEntity chapeterNew = chapeterMapper.mapToEntity(chapeterRest);
        /*
         * Esto lo hacen así en el ejemplo de Spotify, que pasaría si queremos cambiar
         * solo algunos argumentos y no todos? Que pasa si la petición POST no incluye
         * todos los parámetros porque no quiere editarlos todos?
         */
        chapeterNew.setName(chapeterOld.getName());
        chapeterNew.setNumber(0);
        chapeterNew.setDuration(0);
        chapeterNew.setActors(null);
        
        chapeterRepository.save(chapeterNew);

        return chapeterMapper.mapToRestPost(chapeterNew);
    }

    @Override
    @Transactional
    public void deleteChapeter(Long id) throws NetflixException {
        chapeterRepository.deleteById(id);

    }
}