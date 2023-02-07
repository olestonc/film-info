package com.nttdata.bootcamp.ws.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.exception.NetflixNotFoundException;
import com.nttdata.bootcamp.service.TvShowService;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowWithCategoryDTO;
import com.nttdata.bootcamp.service.responseModel.responseTvShow.TvShowWithSeasonDTO;
import com.nttdata.bootcamp.util.constant.CommonConstantsUtils;
import com.nttdata.bootcamp.util.constant.ExceptionConstantsUtils;
import com.nttdata.bootcamp.util.constant.RestConstantsUtils;

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
public class TvShowController {

        private final TvShowService tvshowService;

        @GetMapping(value = RestConstantsUtils.RESOURCE_TVSHOW, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getAllTvShows", description = "Get all TvShows paginated")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<D4iPageRest<TvShowResponseDTO>> getAllTvShows(
                        @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
                        @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
                        @Parameter(hidden = true) final Pageable pageable) {

                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK, tvshowService.getAllTvShows(pageable));
        }

        @GetMapping(value = RestConstantsUtils.RESOURCE_TVSHOW
                        + RestConstantsUtils.RESOURCE_TVSHOWID, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getTvShowById", description = "Get a tvshow by its id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<TvShowResponseDTO> getTvShowById(@RequestParam final Long tvshowId) {
                try {
                        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                        CommonConstantsUtils.OK, tvshowService.getTvShowById(tvshowId));
                } catch (NetflixNotFoundException e) {
                        return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(),
                                        String.valueOf(HttpStatus.NOT_FOUND.value()),
                                        ExceptionConstantsUtils.NOT_FOUND_GENERIC);
                }

        }

        @PostMapping(value = RestConstantsUtils.RESOURCE_TVSHOW, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "createTvShow", description = "createTvShow")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<TvShowResponseDTO> createTvShow(@RequestBody final TvShowResponseDTO tvshow) {
                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK, tvshowService.createTvShow(tvshow));

        }

        @PutMapping(value = RestConstantsUtils.RESOURCE_TVSHOW)
        @Operation(summary = "updateTvShow", description = "updateTvShow")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<TvShowResponseDTO> updateTvShow(@RequestBody final TvShowResponseDTO tvshow) {
                try {
                        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                        CommonConstantsUtils.OK, tvshowService.updateTvShow(tvshow));
                } catch (NetflixNotFoundException e) {
                        return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(),
                                        String.valueOf(HttpStatus.NOT_FOUND.value()),
                                        ExceptionConstantsUtils.NOT_FOUND_GENERIC);
                }

        }

        @DeleteMapping(value = RestConstantsUtils.RESOURCE_TVSHOW + RestConstantsUtils.RESOURCE_TVSHOWID)
        @Operation(summary = "deleteTvShows", description = "Delte a certain tvshow")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<TvShowResponseDTO> deleteTvShow(@RequestParam final Long id) {
                tvshowService.deleteTvShow(id);
                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK);
        }

        @PostMapping(value = RestConstantsUtils.RESOURCE_TVSHOW
                        + RestConstantsUtils.RESOURCE_TVSHOWID + RestConstantsUtils.RESOURCE_CATEGORY
                        + RestConstantsUtils.RESOURCE_CATEGORYID, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "setCategoryToTvShow", description = "set an existing category to an existing tv show")
        public NetflixResponse<TvShowWithCategoryDTO> setCategoryToTvShow(
                        @PathVariable final Long tvshowId, @PathVariable final Long categoryId) {
                try {
                        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                        CommonConstantsUtils.OK,
                                        tvshowService.setCategoryOfTvShow(tvshowId, categoryId));
                } catch (NetflixNotFoundException e) {
                        return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(),
                                        String.valueOf(HttpStatus.NOT_FOUND.value()),
                                        ExceptionConstantsUtils.NOT_FOUND_GENERIC);
                }

        }

        @DeleteMapping(value = RestConstantsUtils.RESOURCE_TVSHOW + RestConstantsUtils.RESOURCE_TVSHOWID
                        + RestConstantsUtils.RESOURCE_CATEGORY)
        @Operation(summary = "deleteCategoryOfTvShow", description = "Delete an existing category from an existing tv show")
        public NetflixResponse<TvShowResponseDTO> deleteCategoryOnTvShow(@PathVariable final Long tvshowId) {
                try {
                        tvshowService.deleteCategoryFromTvShow(tvshowId);
                        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                        CommonConstantsUtils.OK);
                } catch (NetflixNotFoundException e) {
                        return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(),
                                        String.valueOf(HttpStatus.NOT_FOUND.value()),
                                        ExceptionConstantsUtils.NOT_FOUND_GENERIC);
                }
        }

        @PostMapping(value = RestConstantsUtils.RESOURCE_TVSHOW
                        + RestConstantsUtils.RESOURCE_TVSHOWID + RestConstantsUtils.RESOURCE_SEASON
                        + RestConstantsUtils.RESOURCE_SEASONID, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "addSeasonToTvShow", description = "Add an existing season to an existing tv show")
        public NetflixResponse<TvShowWithSeasonDTO> addSeasonToTvShow(@PathVariable final Long tvshowId,
                        @PathVariable final Long seasonId) {
                try {
                        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                        CommonConstantsUtils.OK, tvshowService.addSeasonOfTvShow(tvshowId, seasonId));
                } catch (NetflixNotFoundException e) {
                        return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(),
                                        String.valueOf(HttpStatus.NOT_FOUND.value()),
                                        ExceptionConstantsUtils.NOT_FOUND_GENERIC);
                }

        }

        @DeleteMapping(value = RestConstantsUtils.RESOURCE_TVSHOW + RestConstantsUtils.RESOURCE_TVSHOWID
                        + RestConstantsUtils.RESOURCE_SEASON)
        @Operation(summary = "deleteSeasonOfTvShow", description = "Delete an existing category from an existing tv show")
        public NetflixResponse<TvShowResponseDTO> deleteSeasonOnTvShow(
                        @PathVariable final Long tvshowId, @PathVariable final Long seasonId) {
                try {
                        tvshowService.deleteSeasonOfTvShow(tvshowId, seasonId);
                        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                        CommonConstantsUtils.OK);
                } catch (NetflixNotFoundException e) {
                        return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(),
                                        String.valueOf(HttpStatus.NOT_FOUND.value()),
                                        ExceptionConstantsUtils.NOT_FOUND_GENERIC);
                }
        }

}
