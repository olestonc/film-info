package com.nttdata.bootcamp.ws.controller;

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

import com.nttdata.bootcamp.exception.NetflixException;
import com.nttdata.bootcamp.service.ChapeterService;
import com.nttdata.bootcamp.service.responseModel.ChapeterRest;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.D4iPaginationInfo;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.restChapeter.ChapeterRestPost;
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
@Tag(name = "Chapeter", description = "Chapeter controller")
public class ChapeterControllerRestImpl implements ChapeterControllerRest {

    private final ChapeterService chapeterService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_CHAPETER, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getAllChapeters", description = "Get all Chapeters paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public NetflixResponse<D4iPageRest<ChapeterRest>> getAllChapeters(
            @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
            @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
            @Parameter(hidden = true) final Pageable pageable) throws NetflixException {

        final Page<ChapeterRest> chapeterRestList = chapeterService.getAllChapeters(pageable);
        return new NetflixResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(chapeterRestList.getContent().toArray(ChapeterRest[]::new),
                        new D4iPaginationInfo(chapeterRestList.getNumber(),
                                pageable.getPageSize(),
                                chapeterRestList.getTotalPages())));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_CHAPETER
            + RestConstantsUtils.RESOURCE_CHAPETERID, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getChapeterById", description = "Get a chapeter by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public NetflixResponse<ChapeterRest> getChapeterById(@RequestParam final Long id) throws NetflixException {
        final ChapeterRest chapeterRest = chapeterService.getChapeterById(id);
        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, chapeterRest);

    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstantsUtils.RESOURCE_CHAPETER, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "createChapeter", description = "createChapeter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public NetflixResponse<ChapeterRestPost> createChapeter(@RequestBody final ChapeterRestPost chapeter)
            throws NetflixException {
        final ChapeterRestPost chapeterRest = chapeterService.createChapeter(chapeter);
        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, chapeterRest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = RestConstantsUtils.RESOURCE_CHAPETER)
    @Operation(summary = "updateChapeter", description = "updateChapeter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public NetflixResponse<ChapeterRestPost> updateChapeter(@RequestBody final ChapeterRestPost chapeter)
            throws NetflixException {
        return new NetflixResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, chapeterService.updateChapeter(chapeter));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_CHAPETER + RestConstantsUtils.RESOURCE_CHAPETERID)
    @Operation(summary = "deleteChapeters", description = "Delte a certain chapeter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public void deleteChapeter(@RequestParam final Long id) throws NetflixException {
        chapeterService.deleteChapeter(id);
    }

}
