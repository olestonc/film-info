package com.nttdata.bootcamp.ws.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.service.SeasonService;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseSeason.SeasonWithChapetersResponseDTO;
import com.nttdata.bootcamp.util.constant.CommonConstantsUtils;
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
@Tag(name = "Season", description = "Season controller")
public class SeasonController {

        private final SeasonService seasonService;

        @GetMapping(value = RestConstantsUtils.RESOURCE_SEASON, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getAllSeasons", description = "Get all Seasons paginated")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200")
        })
        public NetflixResponse<D4iPageRest<SeasonResponseDTO>> getAllSeasons(
                        @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
                        @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
                        @Parameter(hidden = true) final Pageable pageable) {

                return seasonService.getAllSeasons(pageable);
        }

        @GetMapping(value = RestConstantsUtils.RESOURCE_SEASON
                        + RestConstantsUtils.RESOURCE_SEASONID, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getSeasonById", description = "Get a season by its id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
        })
        public NetflixResponse<SeasonResponseDTO> getSeasonById(@RequestParam final Long id) {
                return seasonService.getSeasonById(id);
        }

        @PostMapping(value = RestConstantsUtils.RESOURCE_SEASON, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "createSeason", description = "createSeason")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<SeasonResponseDTO> createSeason(@RequestBody final SeasonResponseDTO season) {
                return seasonService.createSeason(season);

        }

        @PutMapping(value = RestConstantsUtils.RESOURCE_SEASON)
        @Operation(summary = "updateSeason", description = "updateSeason")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<SeasonResponseDTO> updateSeason(@RequestBody final SeasonResponseDTO season) {
                return seasonService.updateSeason(season);
        }

        @DeleteMapping(value = RestConstantsUtils.RESOURCE_SEASON + RestConstantsUtils.RESOURCE_SEASONID)
        @Operation(summary = "deleteSeasons", description = "Delte a certain season")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200")
        })
        public NetflixResponse<SeasonResponseDTO> deleteSeason(@RequestParam final Long id) {
                return seasonService.deleteSeason(id);
        }

        @PatchMapping(value = RestConstantsUtils.RESOURCE_SEASON + RestConstantsUtils.RESOURCE_SEASONID + RestConstantsUtils.RESOURCE_CHAPETER +  RestConstantsUtils.RESOURCE_CHAPETERID)
        @Operation(summary = "deleteChapeterFromSeason")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200")
        })
        public NetflixResponse<SeasonResponseDTO> deleteChapeterFromSeason(@RequestParam final Long seasonId,
                        @RequestParam final Long chapeterId) {
                return seasonService.deleteChapeterFromSeason(seasonId, chapeterId);
        }

        @PatchMapping(value = RestConstantsUtils.RESOURCE_SEASON + RestConstantsUtils.RESOURCE_SEASONID)
        @Operation(summary = "addChapeterFromSeason")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200")
        })
        public NetflixResponse<SeasonWithChapetersResponseDTO> addChapeterFromSeason(@RequestParam final Long seasonId,
                        @RequestParam final Long chapeterId) {
                return seasonService.addChapeterFromSeason(seasonId, chapeterId);
        }
}