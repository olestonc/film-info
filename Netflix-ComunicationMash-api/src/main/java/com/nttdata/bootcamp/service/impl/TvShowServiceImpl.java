package com.nttdata.bootcamp.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.exception.error.ErrorDto;
import com.nttdata.bootcamp.mapper.TvShowMapper;
import com.nttdata.bootcamp.persistence.entity.CategoryEntity;
import com.nttdata.bootcamp.persistence.entity.SeasonEntity;
import com.nttdata.bootcamp.persistence.entity.TvShowEntity;
import com.nttdata.bootcamp.persistence.repository.CategoryRepository;
import com.nttdata.bootcamp.persistence.repository.SeasonRepository;
import com.nttdata.bootcamp.persistence.repository.TvShowRepository;
import com.nttdata.bootcamp.service.TvShowService;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.D4iPaginationInfo;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowWithCategoryDTO;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowWithSeasonDTO;
import com.nttdata.bootcamp.util.constant.CommonConstantsUtils;
import com.nttdata.bootcamp.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TvShowServiceImpl implements TvShowService {

    private final TvShowRepository tvShowRepository;

    private final TvShowMapper tvShowMapper;

    private SeasonRepository seasonRepository;

    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public NetflixResponse<D4iPageRest<TvShowResponseDTO>> getAllTvShows(Pageable pageable) {
        final Page<TvShowEntity> page = tvShowRepository.findAll(pageable);
        return new NetflixResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(page.map(tvShowMapper::mapEntityToResponseDTO).getContent().toArray(
                        TvShowResponseDTO[]::new),
                        new D4iPaginationInfo(page.getNumber(),
                                pageable.getPageSize(),
                                page.getTotalPages())));
    }

    @Override
    @Transactional(readOnly = true)
    public NetflixResponse<TvShowResponseDTO> getTvShowById(Long id) {
        try {
            TvShowEntity tvShow = tvShowRepository.findById(id)
                    .orElseThrow(
                            () -> new NetflixNotFoundException(
                                    new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK, tvShowMapper.mapEntityToResponseDTO(tvShow));

        } catch (NetflixNotFoundException netflixNotFoundException) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<TvShowResponseDTO> createTvShow(TvShowResponseDTO tvShow) {
        TvShowEntity tvShowEntity = tvShowMapper.mapResponseDTOToEntity(tvShow);
        tvShowRepository.save(tvShowEntity);// Donde se valida que un tvShow no tiene datos inválidos?
        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, tvShow);
    }

    @Override
    @Transactional
    public NetflixResponse<TvShowResponseDTO> updateTvShow(TvShowResponseDTO tvShow) {
        try {
            TvShowEntity tvShowOld = tvShowRepository.findById(tvShow.getTvShowId()).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
            TvShowEntity tvShowNew = tvShowMapper.mapResponseDTOToEntity(tvShow);
            /*
             * Esto lo hacen así en el ejemplo de Spotify, que pasaría si queremos cambiar
             * solo algunos argumentos y no todos? Que pasa si la petición POST no incluye
             * todos los parámetros porque no quiere editarlos todos?
             */
            tvShowNew.setTvShowName(tvShowOld.getTvShowName());
            tvShowNew.setTvShowShortDescription(tvShowOld.getTvShowShortDescription());
            tvShowNew.setTvShowLongDescription(tvShowOld.getTvShowLongDescription());
            tvShowNew.setTvShowYear(tvShowOld.getTvShowYear());
            tvShowNew.setTvShowRecommendedAge(tvShowOld.getTvShowRecommendedAge());
            tvShowNew.setTvShowAdvertising(tvShowOld.isTvShowAdvertising());
            tvShowRepository.save(tvShowNew);
            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK, tvShowMapper.mapEntityToResponseDTO(tvShowNew));
        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<TvShowResponseDTO> deleteTvShow(Long id) {
        tvShowRepository.deleteById(id);
        return new NetflixResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()), CommonConstantsUtils.OK);
    }

    @Override
    @Transactional
    public NetflixResponse<TvShowWithSeasonDTO> addSeasonOfTvShow(Long tvShowId, Long seasonId) {
        try {
            TvShowEntity tvshow = tvShowRepository.findById(tvShowId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
            SeasonEntity seasonToAdd = seasonRepository.findById(seasonId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            tvshow.getTvShowSeasons().add(seasonToAdd);
            tvShowRepository.save(tvshow);

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK, tvShowMapper.mapEntityToResponseDTOWithSeason(tvshow));
        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<TvShowResponseDTO> deleteSeasonOfTvShow(Long tvShowId, Long seasonId) {
        try {
            TvShowEntity tvshow = tvShowRepository.findById(tvShowId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
            SeasonEntity seasonToRemove = seasonRepository.findById(seasonId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            tvshow.getTvShowSeasons().remove(seasonToRemove);
            tvShowRepository.save(tvshow);

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK);
        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<TvShowWithCategoryDTO> setCategoryOfTvShow(Long tvShowId, Long categoryId) {
        try {
            TvShowEntity tvshow = tvShowRepository.findById(tvShowId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
            CategoryEntity categoryToSet = categoryRepository.findById(categoryId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            tvshow.setTvShowCategory(categoryToSet);
            tvShowRepository.save(tvshow);

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK, tvShowMapper.mapEntityToResponseDTOWithCategory(tvshow));
        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

    @Override
    @Transactional
    public NetflixResponse<TvShowResponseDTO> deleteCategoryFromTvShow(Long tvShowId) {
        try {
            TvShowEntity tvshow = tvShowRepository.findById(tvShowId).orElseThrow(
                    () -> new NetflixNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

            tvshow.setTvShowCategory(null);
            tvShowRepository.save(tvshow);

            return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                    CommonConstantsUtils.OK);
        } catch (NetflixNotFoundException e) {
            return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(), String.valueOf(HttpStatus.NOT_FOUND.value()),
                    ExceptionConstantsUtils.NOT_FOUND_GENERIC);
        }
    }

}