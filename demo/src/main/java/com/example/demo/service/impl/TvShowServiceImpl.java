package com.example.demo.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.rest.model.restTvShow.TvShowRestCategory;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestSeason;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestShort;
import com.example.demo.exception.NetflixException;
import com.example.demo.exception.NetflixNotFoundException;
import com.example.demo.exception.error.ErrorDto;
import com.example.demo.mapper.TvShowMapper;
import com.example.demo.persistence.entity.CategoryEntity;
import com.example.demo.persistence.entity.SeasonEntity;
import com.example.demo.persistence.entity.TvShowEntity;
import com.example.demo.persistence.repository.CategoryRepository;
import com.example.demo.persistence.repository.SeasonRepository;
import com.example.demo.persistence.repository.TvShowRepository;
import com.example.demo.service.TvShowService;
import com.example.demo.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TvShowServiceImpl implements TvShowService {

    private final TvShowRepository tvShowRepository;

    private final TvShowMapper tvShowMapper;

    // private CrudRepository<TvShowEntity, Long> seasonRepository;
    private SeasonRepository seasonRepository;

    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<TvShowRestShort> getAllTvShows(Pageable pageable) throws NetflixException {
        Page<TvShowEntity> page = tvShowRepository.findAll(pageable);
        return page.map(tvShowMapper::mapToRestShort);
    }

    @Override
    @Transactional(readOnly = true)
    public TvShowRestShort getTvShowById(Long id) throws NetflixException {
        /*
         * Equivale a usar la clase optional para recibir de repositorio y
         * if(optional.get()==null) then throws Not Found
         */
        TvShowEntity tvshow = tvShowRepository.findById(id)
                .orElseThrow(
                        () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return tvShowMapper.mapToRestShort(tvshow);
    }

    @Override
    @Transactional
    public TvShowRestShort createTvShow(TvShowRestShort tvshowRest) throws NetflixException {
        TvShowEntity tvshowEntity = tvShowMapper.mapToEntity(tvshowRest);
        tvShowRepository.save(tvshowEntity);// Donde se valida que un tvshow no tiene datos inválidos?
        return tvshowRest;
    }

    @Override
    @Transactional
    public TvShowRestShort updateTvShow(TvShowRestShort tvshowRest) throws NetflixException {
        TvShowEntity tvshowOld = tvShowRepository.findById(tvshowRest.getId()).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        TvShowEntity tvshowNew = tvShowMapper.mapToEntity(tvshowRest);
        /*
         * Esto lo hacen así en el ejemplo de Spotify, que pasaría si queremos cambiar
         * solo algunos argumentos y no todos? Que pasa si la petición POST no incluye
         * todos los parámetros porque no quiere editarlos todos?
         */
        tvshowNew.setName(tvshowOld.getName());
        tvshowNew.setShortDescription(tvshowOld.getShortDescription());
        tvshowNew.setLongDescription(tvshowOld.getLongDescription());
        tvshowNew.setYear(tvshowOld.getYear());
        tvshowNew.setRecommendedAge(tvshowOld.getRecommendedAge());
        tvshowNew.setAdvertising(tvshowOld.isAdvertising());
        tvShowRepository.save(tvshowNew);

        return tvShowMapper.mapToRestShort(tvshowNew);
    }

    @Override
    @Transactional
    public void deleteTvShow(Long id) throws NetflixException {
        tvShowRepository.deleteById(id);

    }

    @Override
    @Transactional
    public TvShowRestSeason addSeasonOfTvShow(Long tvshowId, Long seasonId) throws NetflixException {
        TvShowEntity tvShowEntity = tvShowRepository.findById(
                tvshowId).orElseThrow(
                        () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        Set<SeasonEntity> tvShowEntitySeasons = tvShowEntity.getSeasons();
        SeasonEntity seasonEntity = seasonRepository.findById(
                seasonId).orElseThrow(
                        () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        tvShowEntitySeasons.add(seasonEntity);
        tvShowEntity.setSeasons(tvShowEntitySeasons);
        tvShowRepository.save(tvShowEntity);
        return tvShowMapper.mapToRestSeason(tvShowEntity);

    }

    @Override
    @Transactional
    public TvShowRestCategory addCategoryOfTvShow(Long tvshowId, Long categoryId) throws NetflixException {
        TvShowEntity tvShowEntity = tvShowRepository.findById(tvshowId).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

        tvShowEntity.setCategory(categoryEntity);
        tvShowRepository.save(tvShowEntity);
        return tvShowMapper.mapToRestCategory(tvShowEntity);
    }

    @Override
    @Transactional
    public void deleteSeasonOfTvShow(Long tvshowId, Long seasonId) throws NetflixException {
        TvShowEntity tvShowEntity = tvShowRepository.findById(tvshowId).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        SeasonEntity seasonEntity = seasonRepository.findById(seasonId).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        tvShowEntity.getSeasons().remove(seasonEntity);
        tvShowRepository.save(tvShowEntity);
    }

    @Override
    @Transactional
    public void deleteCategoryOfTvShow(Long tvshowId) throws NetflixException {
        TvShowEntity tvShowEntity = tvShowRepository.findById(tvshowId).orElseThrow(
                () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        tvShowEntity.setCategory(null);
        tvShowRepository.save(tvShowEntity);

    }

}