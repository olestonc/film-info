package com.nttdata.bootcamp.ws.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.nttdata.bootcamp.service.ActorService;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.restActor.ActorRequestDTO;
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
@Tag(name = "Actor", description = "Actor controller")
public class ActorControllerRestImpl {

        private final ActorService actorService;

        @GetMapping(value = RestConstantsUtils.RESOURCE_ACTOR, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getAllActors", description = "Get all Actors paginated")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200")
        })
        public NetflixResponse<D4iPageRest<ActorRequestDTO>> getAllActors(
                        @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
                        @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
                        @Parameter(hidden = true) final Pageable pageable) {

                return actorService.getAllActors(pageable);
        }

        @GetMapping(value = RestConstantsUtils.RESOURCE_ACTOR
                        + RestConstantsUtils.RESOURCE_ACTORID, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getActorById", description = "Get a actor by its id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
        })
        public NetflixResponse<ActorRequestDTO> getActorById(@RequestParam final Long id) {
                return actorService.getActorById(id);

        }

        @PostMapping(value = RestConstantsUtils.RESOURCE_ACTOR, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "createActor", description = "createActor")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200")
        })
        public NetflixResponse<ActorRequestDTO> createActor(@RequestBody final ActorRequestDTO actor) {
                return actorService.createActor(actor);
        }

        @PutMapping(value = RestConstantsUtils.RESOURCE_ACTOR)
        @Operation(summary = "updateActor", description = "updateActor")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
        })
        public NetflixResponse<ActorRequestDTO> updateActor(@RequestBody final ActorRequestDTO actor) {
                return actorService.updateActor(actor);
        }

        @DeleteMapping(value = RestConstantsUtils.RESOURCE_ACTOR + RestConstantsUtils.RESOURCE_ACTORID)
        @Operation(summary = "deleteActors", description = "Delte a certain actor")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200")
        })
        public void deleteActor(@RequestParam final Long id) {
                actorService.deleteActor(id);
        }

}
