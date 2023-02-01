package com.nttdata.bootcamp.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.bootcamp.controller.rest.model.ChapeterRest;
import com.nttdata.bootcamp.controller.rest.model.restChapeter.ChapeterRestPost;
import com.nttdata.bootcamp.exception.NetflixException;
import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.exception.error.ErrorDto;
import com.nttdata.bootcamp.mapper.ChapeterMapper;
import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;
import com.nttdata.bootcamp.persistence.repository.ChapeterRepository;
import com.nttdata.bootcamp.service.ChapeterService;
import com.nttdata.bootcamp.util.constant.ExceptionConstantsUtils;

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
        ChapeterEntity chapeterEntity = chapeterMapper.mapRequestDTOToEntity(chapeterRest);
        chapeterRepository.save(chapeterEntity);// Donde se valida que un chapeter no tiene datos inválidos?
        return chapeterRest;
    }

    @Override
    @Transactional
    public ChapeterRestPost updateChapeter(ChapeterRestPost chapeterRest) throws NetflixException {
        ChapeterEntity chapeterOld = chapeterRepository.findById(chapeterRest.getActorId()).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        ChapeterEntity chapeterNew = chapeterMapper.mapRequestDTOToEntity(chapeterRest);
        /*
         * Esto lo hacen así en el ejemplo de Spotify, que pasaría si queremos cambiar
         * solo algunos argumentos y no todos? Que pasa si la petición POST no incluye
         * todos los parámetros porque no quiere editarlos todos?
         */
        chapeterNew.setName(chapeterOld.getName());
        chapeterNew.setChapeterNumber(0);
        chapeterNew.setChapeterDuration(0);
        chapeterNew.setChapeterActors(null);
        
        chapeterRepository.save(chapeterNew);

        return chapeterMapper.mapToRestPost(chapeterNew);
    }

    @Override
    @Transactional
    public void deleteChapeter(Long id) throws NetflixException {
        chapeterRepository.deleteById(id);

    }
}