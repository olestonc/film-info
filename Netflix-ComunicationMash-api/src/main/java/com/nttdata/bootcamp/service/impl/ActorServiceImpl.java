package com.nttdata.bootcamp.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.restActor.ActorRequestDTO;
import com.nttdata.bootcamp.service.responseModel.restActor.ActorWithChapetersRequestDTO;
import com.nttdata.bootcamp.util.constant.CommonConstantsUtils;
import com.nttdata.bootcamp.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    private final SeasonRepository seasonRepository;

    private final ActorMapper actorMapper;

    @Override
    @Transactional(readOnly = true)
    public NetflixResponse<D4iPageRest<ActorRequestDTO>> getAllActors(Pageable pageable) {
        final Page<ActorEntity> page = actorRepository.findAll(pageable);
        return new NetflixResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(page.map(actorMapper::mapEntityToRequestDTO).getContent().toArray(
                        ActorRequestDTO[]::new),
                        new D4iPaginationInfo(page.getNumber(),
                                pageable.getPageSize(),
                                page.getTotalPages())));

    }

    @Override
    @Transactional(readOnly = true)
    public NetflixResponse<ActorRequestDTO> getActorById(Long id) {
        try {
            ActorEntity actor = actorRepository.findById(id)
                    .orElseThrow(
                            () -> new NetflixNotFoundException(
                                    new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK, actorMapper.mapEntityToRequestDTO(actor));

        } catch (NetflixNotFoundException netflixNotFoundException) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<ActorRequestDTO> createActor(ActorRequestDTO actorRest) {
        ActorEntity actorEntity = actorMapper.mapRequestDTOToEntity(actorRest);
        actorRepository.save(actorEntity);// Donde se valida que un actor no tiene datos inválidos?
        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, actorRest);
    }

    @Override
    @Transactional
    public NetflixResponse<ActorRequestDTO> updateActor(ActorRequestDTO actorRest) {
        try {
            ActorEntity actorOld;
            actorOld = actorRepository.findById(actorRest.getActorId()).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
            ActorEntity actorNew = actorMapper.mapRequestDTOToEntity(actorRest);
            /*
             * Esto lo hacen así en el ejemplo de Spotify, que pasaría si queremos cambiar
             * solo algunos argumentos y no todos? Que pasa si la petición POST no incluye
             * todos los parámetros porque no quiere editarlos todos?
             */
            actorNew.setActorName(actorOld.getActorName());
            actorNew.setActorDescription(actorOld.getActorDescription());
            actorRepository.save(actorNew);
            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK, actorMapper.mapEntityToRequestDTO(actorNew));
        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public void deleteActor(Long id) {
        actorRepository.deleteById(id);

    }

    @Override
    public NetflixResponse<ActorWithChapetersRequestDTO> getActorWithChapetersById(Long id) {
        try {
            ActorEntity actor = actorRepository.findById(id).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            ActorWithChapetersRequestDTO responseActor = actorMapper.mapEntityToWithChapetersRequestDTO(actor);

            Set<TvShowEntity> tvShows = new HashSet<>();

            actor.getActorChapeters().forEach((chapeter) -> {
                tvShows.add(seasonRepository.findById(chapeter.getChapeterId()).get().getSeasonTvShow());
            });

            responseActor.setActorTvShows(tvShows);

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK, responseActor);

        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);

        }
    }

}