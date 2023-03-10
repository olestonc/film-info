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
import com.nttdata.bootcamp.service.ChapeterService;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterResponseDTO;
import com.nttdata.bootcamp.service.responseModel.responseChapeter.ChapeterWithActorsResponseDTO;
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
@Tag(name = "Chapeter", description = "Chapeter controller")
public class ChapeterController {

        private final ChapeterService chapeterService;

        @GetMapping(value = RestConstantsUtils.RESOURCE_CHAPETER, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getAllChapeters", description = "Get all Chapeters paginated")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<D4iPageRest<ChapeterResponseDTO>> getAllChapeters(
                        @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
                        @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
                        @Parameter(hidden = true) final Pageable pageable) {

                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                        CommonConstantsUtils.OK, chapeterService.getAllChapeters(pageable));
        }

        @GetMapping(value = RestConstantsUtils.RESOURCE_CHAPETER
                        + RestConstantsUtils.RESOURCE_CHAPETERID, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getChapeterById", description = "Get a chapeter by its id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<ChapeterResponseDTO> getChapeterById(@PathVariable final Long chapeterId) {
                try {
                        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                        CommonConstantsUtils.OK, chapeterService.getChapeterById(chapeterId));
                } catch (NetflixNotFoundException e) {
                        return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(),
                                        String.valueOf(HttpStatus.NOT_FOUND.value()),
                                        ExceptionConstantsUtils.NOT_FOUND_GENERIC);
                }

        }

        @PostMapping(value = RestConstantsUtils.RESOURCE_CHAPETER, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "createChapeter", description = "createChapeter")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<ChapeterResponseDTO> createChapeter(@RequestBody final ChapeterResponseDTO chapeter) {
                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK, chapeterService.createChapeter(chapeter));

        }

        @PutMapping(value = RestConstantsUtils.RESOURCE_CHAPETER)
        @Operation(summary = "updateChapeter", description = "updateChapeter")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<ChapeterResponseDTO> updateChapeter(@RequestBody final ChapeterResponseDTO chapeter) {
                try {
                        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                        CommonConstantsUtils.OK, chapeterService.updateChapeter(chapeter));
                } catch (NetflixNotFoundException e) {
                        return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(),
                                        String.valueOf(HttpStatus.NOT_FOUND.value()),
                                        ExceptionConstantsUtils.NOT_FOUND_GENERIC);
                }
        }

        @DeleteMapping(value = RestConstantsUtils.RESOURCE_CHAPETER + RestConstantsUtils.RESOURCE_CHAPETERID)
        @Operation(summary = "deleteChapeters", description = "Delte a certain chapeter")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<ChapeterResponseDTO> deleteChapeter(@PathVariable final Long chapeterId) {
                chapeterService.deleteChapeter(chapeterId);
                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK);
        }

        @DeleteMapping(value = RestConstantsUtils.RESOURCE_CHAPETER + RestConstantsUtils.RESOURCE_CHAPETERID
                        + RestConstantsUtils.RESOURCE_ACTOR + RestConstantsUtils.RESOURCE_ACTORID)
        @Operation(summary = "deleteChapeterFromSeason")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "404")
        })
        public NetflixResponse<ChapeterResponseDTO> deleteActorFromChapeter(@PathVariable final Long chapeterId,
                        @PathVariable final Long actorId) {
                try {
                        chapeterService.deleteActorFromChapeter(chapeterId, actorId);
                        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                        CommonConstantsUtils.OK);
                } catch (NetflixNotFoundException e) {
                        return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(),
                                        String.valueOf(HttpStatus.NOT_FOUND.value()),
                                        ExceptionConstantsUtils.NOT_FOUND_GENERIC);
                }
        }

        @PostMapping(value = RestConstantsUtils.RESOURCE_CHAPETER + RestConstantsUtils.RESOURCE_CHAPETERID
                        + RestConstantsUtils.RESOURCE_ACTOR + RestConstantsUtils.RESOURCE_ACTORID)
        @Operation(summary = "addActorFromChapeter")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "404")
        })
        public NetflixResponse<ChapeterWithActorsResponseDTO> addActorFromChapeter(@PathVariable final Long chapeterId,
                        @PathVariable final Long actorId) {
                try {
                        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                        CommonConstantsUtils.OK,
                                        chapeterService.addActorFromChapeter(chapeterId, actorId));
                } catch (NetflixNotFoundException e) {
                        return new NetflixResponse<>(HttpStatus.NOT_FOUND.toString(),
                                        String.valueOf(HttpStatus.NOT_FOUND.value()),
                                        ExceptionConstantsUtils.NOT_FOUND_GENERIC);
                }
        }

}
