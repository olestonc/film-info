package com.nttdata.bootcamp.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterWithActorsResponseDTO;
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
        public D4iPageRest<ChapeterResponseDTO> getAllChapeters(Pageable pageable) {
                final Page<ChapeterEntity> page = chapeterRepository.findAll(pageable);
                return new D4iPageRest<>(page.map(chapeterMapper::mapEntityToResponseDTO).getContent().toArray(
                                ChapeterResponseDTO[]::new),
                                new D4iPaginationInfo(page.getNumber(),
                                                pageable.getPageSize(),
                                                page.getTotalPages()));

        }

        @Override
        @Transactional(readOnly = true)
        public ChapeterResponseDTO getChapeterById(Long id) throws NetflixNotFoundException {

                ChapeterEntity chapeter = chapeterRepository.findById(id)
                                .orElseThrow(() -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

                return chapeterMapper.mapEntityToResponseDTO(chapeter);

        }

        @Override
        @Transactional
        public ChapeterResponseDTO createChapeter(ChapeterResponseDTO chapeter) {
                ChapeterEntity chapeterEntity = chapeterMapper.mapResponseDTOToEntity(chapeter);
                chapeterRepository.save(chapeterEntity);// Donde se valida que un chapeter no tiene datos inv??lidos?
                return chapeter;
        }

        @Override
        @Transactional
        public ChapeterResponseDTO updateChapeter(ChapeterResponseDTO chapeter) throws NetflixNotFoundException {

                ChapeterEntity chapeterOld;
                chapeterOld = chapeterRepository.findById(chapeter.getChapeterId()).orElseThrow(
                                () -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
                ChapeterEntity chapeterNew = chapeterMapper.mapResponseDTOToEntity(chapeter);
                /*
                 * Esto lo hacen as?? en el ejemplo de Spotify, que pasar??a si queremos cambiar
                 * solo algunos argumentos y no todos? Que pasa si la petici??n POST no incluye
                 * todos los par??metros porque no quiere editarlos todos?
                 */
                chapeterNew.setChapeterNumber(chapeterOld.getChapeterNumber());
                chapeterNew.setChapeterName(chapeterOld.getChapeterName());
                chapeterNew.setChapeterDuration(chapeterOld.getChapeterDuration());
                chapeterRepository.save(chapeterNew);
                return chapeterMapper.mapEntityToResponseDTO(chapeterNew);

        }

        @Override
        @Transactional
        public void deleteChapeter(Long id) {
                chapeterRepository.deleteById(id);

        }

        @Override
        @Transactional
        public void deleteActorFromChapeter(Long chapeterId, Long actorId) throws NetflixNotFoundException {

                ChapeterEntity chapeter = chapeterRepository.findById(chapeterId).orElseThrow(
                                () -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
                ActorEntity actorToDelete = actorRepository.findById(actorId).orElseThrow(
                                () -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));// quiz?? Bad
                                                                                                          // request
                                                                                                          // mejor

                chapeter.getChapeterActors().remove(actorToDelete);
                chapeterRepository.save(chapeter);

        }

        @Override
        @Transactional
        public ChapeterWithActorsResponseDTO addActorFromChapeter(Long chapeterId, Long actorId) throws NetflixNotFoundException {

                ChapeterEntity chapeter = chapeterRepository.findById(chapeterId).orElseThrow(
                                () -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
                ActorEntity actorToAdd = actorRepository.findById(actorId).orElseThrow(
                                () -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

                chapeter.getChapeterActors().add(actorToAdd);
                chapeterRepository.save(chapeter);

                return chapeterMapper.mapEntityToWithActorsResponseDTO(chapeter);

        }

}