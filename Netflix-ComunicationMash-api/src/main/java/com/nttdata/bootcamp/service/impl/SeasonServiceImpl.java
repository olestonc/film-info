package com.nttdata.bootcamp.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.exception.error.ErrorDto;
import com.nttdata.bootcamp.mapper.SeasonMapper;
import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;
import com.nttdata.bootcamp.persistence.entity.SeasonEntity;
import com.nttdata.bootcamp.persistence.repository.ChapeterRepository;
import com.nttdata.bootcamp.persistence.repository.SeasonRepository;
import com.nttdata.bootcamp.service.SeasonService;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.D4iPaginationInfo;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonWithChapetersResponseDTO;
import com.nttdata.bootcamp.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {
        private final ChapeterRepository chapeterRepository;

        private final SeasonRepository seasonRepository;

        private final SeasonMapper seasonMapper;

        @Override
        @Transactional(readOnly = true)
        public D4iPageRest<SeasonResponseDTO> getAllSeasons(Pageable pageable) {
                Page<SeasonEntity> page = seasonRepository.findAll(pageable);
                return new D4iPageRest<>(page.getContent().toArray(SeasonResponseDTO[]::new),
                                new D4iPaginationInfo(page.getNumber(),
                                                pageable.getPageSize(),
                                                page.getTotalPages()));
        }

        @Override
        @Transactional(readOnly = true)
        public SeasonResponseDTO getSeasonById(Long id) throws NetflixNotFoundException {
                /*
                 * Equivale a usar la clase optional para recibir de repositorio y
                 * if(optional.get()==null) then throws Not Found
                 */

                SeasonEntity season = seasonRepository.findById(id)
                                .orElseThrow(() -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
                return seasonMapper.mapEntityToResponseDTO(season);

        }

        @Override
        @Transactional
        public void createSeason(SeasonResponseDTO seasonRest) {
                SeasonEntity seasonEntity = seasonMapper.mapResponseDTOToEntity(seasonRest);
                seasonRepository.save(seasonEntity);// Donde se valida que un season no tiene datos inválidos?
        }

        @Override
        @Transactional
        public SeasonResponseDTO updateSeason(SeasonResponseDTO seasonRest) throws NetflixNotFoundException {

                SeasonEntity seasonOld = seasonRepository.findById(seasonRest.getSeasonId()).orElseThrow(
                                () -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
                SeasonEntity seasonNew = seasonMapper.mapResponseDTOToEntity(seasonRest);
                /*
                 * Esto lo hacen así en el ejemplo de Spotify, que pasaría si queremos cambiar
                 * solo algunos argumentos y no todos? Que pasa si la petición POST no incluye
                 * todos los parámetros porque no quiere editarlos todos?
                 */
                seasonNew.setSeasonName(seasonOld.getSeasonName());
                seasonNew.setSeasonNumber(seasonOld.getSeasonNumber());
                seasonNew.setSeasonLongDescription(seasonOld.getSeasonLongDescription());
                seasonNew.setSeasonChapeters(null);
                seasonRepository.save(seasonNew);

                return seasonMapper.mapEntityToResponseDTO(seasonNew);

        }

        @Override
        @Transactional
        public void deleteSeason(Long id) {
                seasonRepository.deleteById(id);

        }

        @Override
        @Transactional
        public void deleteChapeterFromSeason(Long seasonId, Long chapeterId) throws NetflixNotFoundException {

                SeasonEntity season = seasonRepository.findById(seasonId).orElseThrow(
                                () -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
                ChapeterEntity chapeterToDelete = chapeterRepository.findById(chapeterId).orElseThrow(
                                () -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

                season.getSeasonChapeters().remove(chapeterToDelete);
                seasonRepository.save(season);

        }

        @Override
        @Transactional
        public SeasonWithChapetersResponseDTO addChapeterFromSeason(Long seasonId, Long chapeterId)
                        throws NetflixNotFoundException {

                SeasonEntity season = seasonRepository.findById(seasonId).orElseThrow(
                                () -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
                ChapeterEntity chapeterToAdd = chapeterRepository.findById(chapeterId).orElseThrow(
                                () -> new NetflixNotFoundException(
                                                new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

                season.getSeasonChapeters().add(chapeterToAdd);
                seasonRepository.save(season);

                return seasonMapper.mapEntityToResponseWithChapetersDTO(season);

        }

}