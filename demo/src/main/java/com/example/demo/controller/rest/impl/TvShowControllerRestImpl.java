package com.example.demo.controller.rest.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.rest.TvShowControllerRest;
import com.example.demo.controller.rest.model.D4iPageRest;
import com.example.demo.controller.rest.model.D4iPaginationInfo;
import com.example.demo.controller.rest.model.NetflixResponse;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestCategory;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestSeason;
import com.example.demo.controller.rest.model.restTvShow.TvShowRestShort;
import com.example.demo.exception.NetflixException;
import com.example.demo.mapper.TvShowMapper;
import com.example.demo.service.TvShowService;
import com.example.demo.util.constant.CommonConstantsUtils;
import com.example.demo.util.constant.RestConstantsUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "TvShow", description = "TvShow controller")
public class TvShowControllerRestImpl implements TvShowControllerRest {

        private final TvShowService tvshowService;

        private final TvShowMapper tvShowMapper;

        @Override
        @ResponseStatus(HttpStatus.OK)
        @GetMapping(value = RestConstantsUtils.RESOURCE_TVSHOW, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getAllTvShows", description = "Get all TvShows paginated")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<D4iPageRest<TvShowRestShort>> getAllTvShows(
                        @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
                        @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
                        @Parameter(hidden = true) final Pageable pageable) throws NetflixException {

                final Page<TvShowRestShort> TvShowRestShortList = tvshowService.getAllTvShows(pageable);
                return new NetflixResponse<>(HttpStatus.OK.toString(),
                                String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK,
                                new D4iPageRest<>(TvShowRestShortList.getContent().toArray(TvShowRestShort[]::new),
                                                new D4iPaginationInfo(TvShowRestShortList.getNumber(),
                                                                pageable.getPageSize(),
                                                                TvShowRestShortList.getTotalPages())));
        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @GetMapping(value = RestConstantsUtils.RESOURCE_TVSHOW
                        + RestConstantsUtils.RESOURCE_TVSHOWID, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getTvShowById", description = "Get a tvshow by its id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<TvShowRestShort> getTvShowById(@RequestParam final Long tvshowId)
                        throws NetflixException {
                final TvShowRestShort TvShowRestShort = tvshowService.getTvShowById(tvshowId);
                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK, TvShowRestShort);

        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @PostMapping(value = RestConstantsUtils.RESOURCE_TVSHOW, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "createTvShow", description = "createTvShow")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<TvShowRestShort> createTvShow(@RequestBody final TvShowRestShort tvshow)
                        throws NetflixException {
                final TvShowRestShort tvshowRest = tvshowService.createTvShow(tvshow);
                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK, tvshowRest);
        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @PutMapping(value = RestConstantsUtils.RESOURCE_TVSHOW)
        @Operation(summary = "updateTvShow", description = "updateTvShow")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<TvShowRestShort> updateTvShow(@RequestBody final TvShowRestShort tvshow)
                        throws NetflixException {
                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK, tvshowService.updateTvShow(tvshow));
        }

        @Override
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping(value = RestConstantsUtils.RESOURCE_TVSHOW + RestConstantsUtils.RESOURCE_TVSHOWID)
        @Operation(summary = "deleteTvShows", description = "Delte a certain tvshow")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public void deleteTvShow(@RequestParam final Long id) throws NetflixException {
                tvshowService.deleteTvShow(id);
        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @PostMapping(value = RestConstantsUtils.RESOURCE_TVSHOW
                        + RestConstantsUtils.RESOURCE_TVSHOWID + RestConstantsUtils.RESOURCE_CATEGORY
                        + RestConstantsUtils.RESOURCE_CATEGORYID, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "addCategoryToTvShow", description = "Add an existing category to an existing tv show")
        public NetflixResponse<TvShowRestCategory> addCategoryToTvShow(Long tvshowId, Long categoryId)
                        throws NetflixException {
                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK, tvshowService.addCategoryOfTvShow(tvshowId, categoryId));
        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @PostMapping(value = RestConstantsUtils.RESOURCE_TVSHOW
                        + RestConstantsUtils.RESOURCE_TVSHOWID + RestConstantsUtils.RESOURCE_SEASON
                        + RestConstantsUtils.RESOURCE_SEASONID, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "addSeasonToTvShow", description = "Add an existing season to an existing tv show")
        public NetflixResponse<TvShowRestSeason> addSeasonToTvShow(Long tvshowId, Long seasonId)
                        throws NetflixException {
                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK, tvshowService.addSeasonOfTvShow(tvshowId, seasonId));
        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @DeleteMapping(value = RestConstantsUtils.RESOURCE_TVSHOW
                        + RestConstantsUtils.RESOURCE_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "deleteCategoryOfTvShow", description = "Delete an existing category from an existing tv show")
        public void deleteCategoryOnTvShow(Long tvshowId) throws NetflixException {
                tvshowService.deleteCategoryOfTvShow(tvshowId);
        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @DeleteMapping(value = RestConstantsUtils.RESOURCE_TVSHOW
                        + RestConstantsUtils.RESOURCE_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "deleteSeasonOfTvShow", description = "Delete an existing category from an existing tv show")
        public void deleteSeasonOnTvShow(Long tvshowId, Long seasonId) throws NetflixException {
                tvshowService.deleteSeasonOfTvShow(tvshowId, seasonId);
        }

}
