package com.example.demo.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.rest.model.SeasonRest;
import com.example.demo.controller.rest.model.restSeason.SeasonRestPost;
import com.example.demo.exception.NetflixException;
import com.example.demo.exception.NetflixNotFoundException;
import com.example.demo.exception.error.ErrorDto;
import com.example.demo.mapper.SeasonMapper;
import com.example.demo.persistence.entity.SeasonEntity;
import com.example.demo.persistence.repository.SeasonRepository;
import com.example.demo.service.SeasonService;
import com.example.demo.util.constant.ExceptionConstantsUtils;

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
        SeasonEntity seasonEntity = seasonMapper.mapToEntity(seasonRest);
        seasonRepository.save(seasonEntity);// Donde se valida que un season no tiene datos inválidos?
        return seasonRest;
    }

    @Override
    @Transactional
    public SeasonRestPost updateSeason(SeasonRestPost seasonRest) throws NetflixException {
        SeasonEntity seasonOld = seasonRepository.findById(seasonRest.getId()).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        SeasonEntity seasonNew = seasonMapper.mapToEntity(seasonRest);
        /*
         * Esto lo hacen así en el ejemplo de Spotify, que pasaría si queremos cambiar
         * solo algunos argumentos y no todos? Que pasa si la petición POST no incluye
         * todos los parámetros porque no quiere editarlos todos?
         */
        seasonNew.setName(seasonOld.getName());
        seasonNew.setNumber(seasonOld.getNumber());
        seasonNew.setLongDescription(seasonOld.getLongDescription());
        seasonNew.setChapeters(null);
        seasonRepository.save(seasonNew);

        return seasonMapper.mapToRestPost(seasonNew);
    }

    @Override
    @Transactional
    public void deleteSeason(Long id) throws NetflixException {
        seasonRepository.deleteById(id);

    }
}