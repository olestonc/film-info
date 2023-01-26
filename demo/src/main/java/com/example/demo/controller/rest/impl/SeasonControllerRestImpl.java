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

import com.example.demo.controller.rest.SeasonControllerRest;
import com.example.demo.controller.rest.model.D4iPageRest;
import com.example.demo.controller.rest.model.D4iPaginationInfo;
import com.example.demo.controller.rest.model.NetflixResponse;
import com.example.demo.controller.rest.model.SeasonRest;
import com.example.demo.controller.rest.model.restSeason.SeasonRestPost;
import com.example.demo.exception.NetflixException;
import com.example.demo.service.SeasonService;
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
@Tag(name = "Season", description = "Season controller")
public class SeasonControllerRestImpl implements SeasonControllerRest {

    private final SeasonService seasonService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_SEASON, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getAllSeasons", description = "Get all Seasons paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public NetflixResponse<D4iPageRest<SeasonRest>> getAllSeasons(
            @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
            @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
            @Parameter(hidden = true) final Pageable pageable) throws NetflixException {

        final Page<SeasonRest> seasonRestList = seasonService.getAllSeasons(pageable);
        return new NetflixResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(seasonRestList.getContent().toArray(SeasonRest[]::new),
                        new D4iPaginationInfo(seasonRestList.getNumber(),
                                pageable.getPageSize(),
                                seasonRestList.getTotalPages())));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_SEASON
            + RestConstantsUtils.RESOURCE_SEASONID, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getSeasonById", description = "Get a season by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public NetflixResponse<SeasonRest> getSeasonById(@RequestParam final Long id) throws NetflixException {
        final SeasonRest seasonRest = seasonService.getSeasonById(id);
        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, seasonRest);

    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstantsUtils.RESOURCE_SEASON, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "createSeason", description = "createSeason")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public NetflixResponse<SeasonRestPost> createSeason(@RequestBody final SeasonRestPost season)
            throws NetflixException {
        final SeasonRestPost seasonRest = seasonService.createSeason(season);
        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, seasonRest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = RestConstantsUtils.RESOURCE_SEASON)
    @Operation(summary = "updateSeason", description = "updateSeason")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public NetflixResponse<SeasonRestPost> updateSeason(@RequestBody final SeasonRestPost season)
            throws NetflixException {
        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, seasonService.updateSeason(season));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_SEASON + RestConstantsUtils.RESOURCE_SEASONID)
    @Operation(summary = "deleteSeasons", description = "Delte a certain season")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public void deleteSeason(@RequestParam final Long id) throws NetflixException {
        seasonService.deleteSeason(id);
    }

}