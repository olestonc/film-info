package com.nttdata.bootcamp.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.bootcamp.controller.rest.model.SeasonRest;
import com.nttdata.bootcamp.controller.rest.model.restSeason.SeasonRestPost;
import com.nttdata.bootcamp.exception.NetflixException;
import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.exception.error.ErrorDto;
import com.nttdata.bootcamp.mapper.SeasonMapper;
import com.nttdata.bootcamp.persistence.entity.SeasonEntity;
import com.nttdata.bootcamp.persistence.repository.SeasonRepository;
import com.nttdata.bootcamp.service.SeasonService;
import com.nttdata.bootcamp.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {
    private final SeasonRepository seasonRepository;

    private final SeasonMapper seasonMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<SeasonRest> getAllSeasons(Pageable pageable) throws NetflixException {
        Page<SeasonEntity> page = seasonRepository.findAll(pageable);
        return page.map(seasonMapper::mapToRest);
    }

    @Override
    @Transactional(readOnly = true)
    public SeasonRest getSeasonById(Long id) throws NetflixException {
        /*
         * Equivale a usar la clase optional para recibir de repositorio y
         * if(optional.get()==null) then throws Not Found
         */
        SeasonEntity season = seasonRepository.findById(id)
                .orElseThrow(
                        () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return seasonMapper.mapToRest(season);
    }

    @Override
    @Transactional
    public SeasonRestPost createSeason(SeasonRestPost seasonRest) throws NetflixException {
        SeasonEntity seasonEntity = seasonMapper.mapRequestDTOToEntity(seasonRest);
        seasonRepository.save(seasonEntity);// Donde se valida que un season no tiene datos inválidos?
        return seasonRest;
    }

    @Override
    @Transactional
    public SeasonRestPost updateSeason(SeasonRestPost seasonRest) throws NetflixException {
        SeasonEntity seasonOld = seasonRepository.findById(seasonRest.getActorId()).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        SeasonEntity seasonNew = seasonMapper.mapRequestDTOToEntity(seasonRest);
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

        return seasonMapper.mapToRestPost(seasonNew);
    }

    @Override
    @Transactional
    public void deleteSeason(Long id) throws NetflixException {
        seasonRepository.deleteById(id);

    }
}