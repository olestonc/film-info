package com.example.demo.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.rest.model.ActorRest;
import com.example.demo.controller.rest.model.restActor.ActorRestPost;
import com.example.demo.exception.NetflixException;
import com.example.demo.exception.NetflixNotFoundException;
import com.example.demo.exception.error.ErrorDto;
import com.example.demo.mapper.ActorMapper;
import com.example.demo.persistence.entity.ActorEntity;
import com.example.demo.persistence.repository.ActorRepository;
import com.example.demo.service.ActorService;
import com.example.demo.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    private final ActorMapper actorMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ActorRest> getAllActors(Pageable pageable) throws NetflixException {
        Page<ActorEntity> page = actorRepository.findAll(pageable);
        return page.map(actorMapper::mapToRest);
    }

    @Override
    @Transactional(readOnly = true)
    public ActorRest getActorById(Long id) throws NetflixException {
        /*
         * Equivale a usar la clase optional para recibir de repositorio y
         * if(optional.get()==null) then throws Not Found
         */
        ActorEntity actor = actorRepository.findById(id)
                .orElseThrow(
                        () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return actorMapper.mapToRest(actor);
    }

    @Override
    @Transactional
    public ActorRestPost createActor(ActorRestPost actorRest) throws NetflixException {
        ActorEntity actorEntity = actorMapper.mapToEntity(actorRest);
        actorRepository.save(actorEntity);//Donde se valida que un actor no tiene datos inválidos?
        return actorRest;
    }

    @Override
    @Transactional
    public ActorRestPost updateActor(ActorRestPost actorRest) throws NetflixException {
        ActorEntity actorOld = actorRepository.findById(actorRest.getId()).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        ActorEntity actorNew = actorMapper.mapToEntity(actorRest);
        /*
         * Esto lo hacen así en el ejemplo de Spotify, que pasaría si queremos cambiar
         * solo algunos argumentos y no todos? Que pasa si la petición POST no incluye
         * todos los parámetros porque no quiere editarlos todos?
         */
        actorNew.setName(actorOld.getName());
        actorNew.setDescription(actorOld.getDescription());
        actorRepository.save(actorNew);

        return actorMapper.mapToRestPost(actorNew);
    }

    @Override
    @Transactional
    public void deleteActor(Long id) throws NetflixException {
        actorRepository.deleteById(id);

    }

}