package com.nttdata.bootcamp.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.exception.error.ErrorDto;
import com.nttdata.bootcamp.mapper.ChapeterMapper;
import com.nttdata.bootcamp.persistence.entity.ActorEntity;
import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;
import com.nttdata.bootcamp.persistence.repository.ActorRepository;
import com.nttdata.bootcamp.persistence.repository.ChapeterRepository;
import com.nttdata.bootcamp.service.ChapeterService;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.D4iPaginationInfo;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterWithActorsResponseDTO;
import com.nttdata.bootcamp.util.constant.CommonConstantsUtils;
import com.nttdata.bootcamp.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChapeterServiceImpl implements ChapeterService {
    private final ChapeterRepository chapeterRepository;

    private final ChapeterMapper chapeterMapper;

    private ActorRepository actorRepository;

    @Override
    @Transactional(readOnly = true)
    public NetflixResponse<D4iPageRest<ChapeterResponseDTO>> getAllChapeters(Pageable pageable) {
        final Page<ChapeterEntity> page = chapeterRepository.findAll(pageable);
        return new NetflixResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(page.map(chapeterMapper::mapEntityToResponseDTO).getContent().toArray(
                        ChapeterResponseDTO[]::new),
                        new D4iPaginationInfo(page.getNumber(),
                                pageable.getPageSize(),
                                page.getTotalPages())));

    }

    @Override
    @Transactional(readOnly = true)
    public NetflixResponse<ChapeterResponseDTO> getChapeterById(Long id) {
        try {
            ChapeterEntity chapeter = chapeterRepository.findById(id)
                    .orElseThrow(
                            () -> new NetflixNotFoundException(
                                    new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK, chapeterMapper.mapEntityToResponseDTO(chapeter));

        } catch (NetflixNotFoundException netflixNotFoundException) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<ChapeterResponseDTO> createChapeter(ChapeterResponseDTO chapeter) {
        ChapeterEntity chapeterEntity = chapeterMapper.mapResponseDTOToEntity(chapeter);
        chapeterRepository.save(chapeterEntity);// Donde se valida que un chapeter no tiene datos inválidos?
        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, chapeter);
    }

    @Override
    @Transactional
    public NetflixResponse<ChapeterResponseDTO> updateChapeter(ChapeterResponseDTO chapeter) {
        try {
            ChapeterEntity chapeterOld;
            chapeterOld = chapeterRepository.findById(chapeter.getChapeterId()).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
            ChapeterEntity chapeterNew = chapeterMapper.mapResponseDTOToEntity(chapeter);
            /*
             * Esto lo hacen así en el ejemplo de Spotify, que pasaría si queremos cambiar
             * solo algunos argumentos y no todos? Que pasa si la petición POST no incluye
             * todos los parámetros porque no quiere editarlos todos?
             */
            chapeterNew.setChapeterNumber(chapeterOld.getChapeterNumber());
            chapeterNew.setChapeterName(chapeterOld.getChapeterName());
            chapeterNew.setChapeterDuration(chapeterOld.getChapeterDuration());
            chapeterRepository.save(chapeterNew);
            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK, chapeterMapper.mapEntityToResponseDTO(chapeterNew));
        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<ChapeterResponseDTO> deleteChapeter(Long id) {
        chapeterRepository.deleteById(id);
        return new NetflixResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()), CommonConstantsUtils.OK);

    }

    @Override
    @Transactional
    public NetflixResponse<ChapeterResponseDTO> deleteActorFromChapeter(Long chapeterId, Long actorId) {
        try {
            ChapeterEntity chapeter = chapeterRepository.findById(chapeterId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
            ActorEntity actorToDelete = actorRepository.findById(actorId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            chapeter.getChapeterActors().remove(actorToDelete);
            chapeterRepository.save(chapeter);

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK);
        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<ChapeterWithActorsResponseDTO> addActorFromChapeter(Long chapeterId, Long actorId) {
        try {
            ChapeterEntity chapeter = chapeterRepository.findById(chapeterId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
            ActorEntity actorToAdd = actorRepository.findById(actorId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            chapeter.getChapeterActors().add(actorToAdd);
            chapeterRepository.save(chapeter);

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK, chapeterMapper.mapEntityToWithActorsResponseDTO(chapeter));
        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

}