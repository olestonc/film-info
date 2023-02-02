package com.nttdata.bootcamp.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.exception.error.ErrorDto;
import com.nttdata.bootcamp.mapper.SeasonMapper;
import com.nttdata.bootcamp.persistence.entity.SeasonEntity;
import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;
import com.nttdata.bootcamp.persistence.repository.SeasonRepository;
import com.nttdata.bootcamp.persistence.repository.ChapeterRepository;
import com.nttdata.bootcamp.service.SeasonService;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.D4iPaginationInfo;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonWithChapetersResponseDTO;
import com.nttdata.bootcamp.util.constant.CommonConstantsUtils;
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
    public NetflixResponse<D4iPageRest<SeasonResponseDTO>> getAllSeasons(Pageable pageable) {
        Page<SeasonEntity> page = seasonRepository.findAll(pageable);
        return new NetflixResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(page.getContent().toArray(SeasonResponseDTO[]::new),
                        new D4iPaginationInfo(page.getNumber(),
                                pageable.getPageSize(),
                                page.getTotalPages())));
    }

    @Override
    @Transactional(readOnly = true)
    public NetflixResponse<SeasonResponseDTO> getSeasonById(Long id) {
        /*
         * Equivale a usar la clase optional para recibir de repositorio y
         * if(optional.get()==null) then throws Not Found
         */
        try {
            SeasonEntity season = seasonRepository.findById(id)
                    .orElseThrow(
                            () -> new NetflixNotFoundException(
                                    new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK, seasonMapper.mapEntityToResponseDTO(season));

        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<SeasonResponseDTO> createSeason(SeasonResponseDTO seasonRest) {
        SeasonEntity seasonEntity = seasonMapper.mapResponseDTOToEntity(seasonRest);
        seasonRepository.save(seasonEntity);// Donde se valida que un season no tiene datos inválidos?

        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK);
    }

    @Override
    @Transactional
    public NetflixResponse<SeasonResponseDTO> updateSeason(SeasonResponseDTO seasonRest) {
        try {
            SeasonEntity seasonOld = seasonRepository.findById(seasonRest.getSeasonId()).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
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

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK, seasonMapper.mapEntityToResponseDTO(seasonNew));

        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<SeasonResponseDTO> deleteSeason(Long id) {
        seasonRepository.deleteById(id);
        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK);

    }

    @Override
    @Transactional
    public NetflixResponse<SeasonResponseDTO> deleteChapeterFromSeason(Long seasonId, Long chapeterId) {
        try {
            SeasonEntity season = seasonRepository.findById(seasonId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
            ChapeterEntity chapeterToDelete = chapeterRepository.findById(chapeterId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            season.getSeasonChapeters().remove(chapeterToDelete);
            seasonRepository.save(season);

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK);
        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<SeasonWithChapetersResponseDTO> addChapeterFromSeason(Long seasonId, Long chapeterId) {
        try {
            SeasonEntity season = seasonRepository.findById(seasonId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
            ChapeterEntity chapeterToAdd = chapeterRepository.findById(chapeterId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            season.getSeasonChapeters().add(chapeterToAdd);
            seasonRepository.save(season);

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK,seasonMapper.mapEntityToResponseWithChapetersDTO(season));
        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

}