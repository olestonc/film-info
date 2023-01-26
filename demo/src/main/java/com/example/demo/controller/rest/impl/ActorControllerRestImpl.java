package com.example.demo.controller.rest.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.controller.rest.ActorControllerRest;
import com.example.demo.controller.rest.model.ActorRest;
import com.example.demo.controller.rest.model.D4iPageRest;
import com.example.demo.controller.rest.model.D4iPaginationInfo;
import com.example.demo.controller.rest.model.NetflixResponse;
import com.example.demo.controller.rest.model.restActor.ActorRestPost;
import com.example.demo.exception.NetflixException;
import com.example.demo.service.ActorService;
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
@Tag(name = "Actor", description = "Actor controller")
public class ActorControllerRestImpl implements ActorControllerRest {

        private final ActorService actorService;


        @Override
        @ResponseStatus(HttpStatus.OK)
        @GetMapping(value = RestConstantsUtils.RESOURCE_ACTOR, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getAllActors", description = "Get all Actors paginated")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<D4iPageRest<ActorRest>> getAllActors(
                        @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
                        @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
                        @Parameter(hidden = true) final Pageable pageable) throws NetflixException {

                final Page<ActorRest> actorRestList = actorService.getAllActors(pageable);
                return new NetflixResponse<>(HttpStatus.OK.toString(),
                                String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK,
                                new D4iPageRest<>(actorRestList.getContent().toArray(ActorRest[]::new),
                                                new D4iPaginationInfo(actorRestList.getNumber(),
                                                                pageable.getPageSize(),
                                                                actorRestList.getTotalPages())));
        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @GetMapping(value = RestConstantsUtils.RESOURCE_ACTOR
                        + RestConstantsUtils.RESOURCE_ACTORID, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getActorById", description = "Get a actor by its id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<ActorRest> getActorById(@RequestParam final Long id) throws NetflixException {
                final ActorRest actorRest = actorService.getActorById(id);
                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK, actorRest);

        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @PostMapping(value = RestConstantsUtils.RESOURCE_ACTOR, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "createActor", description = "createActor")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<ActorRestPost> createActor(@RequestBody final ActorRestPost actor)
                        throws NetflixException {
                final ActorRestPost actorRest = actorService.createActor(actor);
                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK, actorRest);
        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @PutMapping(value = RestConstantsUtils.RESOURCE_ACTOR)
        @Operation(summary = "updateActor", description = "updateActor")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<ActorRestPost> updateActor(@RequestBody final ActorRestPost actor)
                        throws NetflixException {
                return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                CommonConstantsUtils.OK, actorService.updateActor(actor));
        }

        @Override
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping(value = RestConstantsUtils.RESOURCE_ACTOR + RestConstantsUtils.RESOURCE_ACTORID)
        @Operation(summary = "deleteActors", description = "Delte a certain actor")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public void deleteActor(@RequestParam final Long id) throws NetflixException {
                actorService.deleteActor(id);
        }

}
