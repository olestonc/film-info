package com.nttdata.bootcamp.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.exception.error.ErrorDto;
import com.nttdata.bootcamp.mapper.ActorMapper;
import com.nttdata.bootcamp.persistence.entity.ActorEntity;
import com.nttdata.bootcamp.persistence.entity.TvShowEntity;
import com.nttdata.bootcamp.persistence.repository.ActorRepository;
import com.nttdata.bootcamp.persistence.repository.SeasonRepository;
import com.nttdata.bootcamp.service.ActorService;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.D4iPaginationInfo;
import com.nttdata.bootcamp.service.responseModel.reponseActor.ActorResponseDTO;
import com.nttdata.bootcamp.service.responseModel.reponseActor.ActorWithChapetersResponseDTO;
import com.nttdata.bootcamp.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    private final SeasonRepository seasonRepository;

    @Autowired
    private ActorMapper actorMapper;

    @Override
    @Transactional(readOnly = true)
    public D4iPageRest<ActorResponseDTO> getAllActors(Pageable pageable) {
        final Page<ActorEntity> page = actorRepository.findAll(pageable);
        return new D4iPageRest<>(page.map(actorMapper::mapEntityToResponseDTO).getContent().toArray(
                ActorResponseDTO[]::new),
                new D4iPaginationInfo(page.getNumber(),
                        pageable.getPageSize(),
                        page.getTotalPages()));

    }

    @Override
    @Transactional(readOnly = true)
    public ActorResponseDTO getActorById(Long id) throws NetflixNotFoundException {

        ActorEntity actor = actorRepository.findById(id)
                .orElseThrow(
                        () -> new NetflixNotFoundException(
                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

        return actorMapper.mapEntityToResponseDTO(actor);

    }

    @Override
    @Transactional
    public ActorResponseDTO createActor(ActorResponseDTO actor) {
        ActorEntity actorEntity = actorMapper.mapResponseDTOToEntity(actor);
        actorRepository.save(actorEntity);// Donde se valida que un actor no tiene datos inválidos?
        return actor;
    }

    @Override
    @Transactional
    public ActorResponseDTO updateActor(ActorResponseDTO actorRest) throws NetflixNotFoundException {

        ActorEntity actorOld;
        actorOld = actorRepository.findById(actorRest.getActorId()).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        ActorEntity actorNew = actorMapper.mapResponseDTOToEntity(actorRest);
        /*
         * Esto lo hacen así en el ejemplo de Spotify, que pasaría si queremos cambiar
         * solo algunos argumentos y no todos? Que pasa si la petición POST no incluye
         * todos los parámetros porque no quiere editarlos todos?
         */
        actorNew.setActorName(actorOld.getActorName());
        actorNew.setActorDescription(actorOld.getActorDescription());
        actorRepository.save(actorNew);
        return actorMapper.mapEntityToResponseDTO(actorNew);

    }

    @Override
    @Transactional
    public void deleteActor(Long id) {
        actorRepository.deleteById(id);

    }

    @Override
    public ActorWithChapetersResponseDTO getActorWithChapetersById(Long id) throws NetflixNotFoundException {

        ActorEntity actor = actorRepository.findById(id).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

        ActorWithChapetersResponseDTO responseActor = actorMapper.mapEntityToWithChapetersResponseDTO(actor);

        Set<TvShowEntity> tvShows = new HashSet<>();

        actor.getActorChapeters().forEach((chapeter) -> {
            tvShows.add(seasonRepository.findById(chapeter.getChapeterId()).get().getSeasonTvShow());
        });

        responseActor.setActorTvShows(tvShows);

        return responseActor;

    }

}