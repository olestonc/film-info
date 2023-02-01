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

import com.nttdata.bootcamp.service.CategoryService;
import com.nttdata.bootcamp.service.responseModel.CategoryRest;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.restCategory.CategoryRestPost;
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
@Tag(name = "Category", description = "Category controller")
public class CategoryControllerRestImpl implements CategoryControllerRest {

        private final CategoryService categoryService;

        @Override
        @ResponseStatus(HttpStatus.OK)
        @GetMapping(value = RestConstantsUtils.RESOURCE_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getAllCategorys", description = "Get all Categorys paginated")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<D4iPageRest<CategoryRest>> getAllCategories(
                        @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
                        @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
                        @Parameter(hidden = true) final Pageable pageable) {

                return categoryService.getAllCategorys(pageable);
        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @GetMapping(value = RestConstantsUtils.RESOURCE_CATEGORY
                        + RestConstantsUtils.RESOURCE_CATEGORYID, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getCategoryById", description = "Get a category by its id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<CategoryRest> getCategoryById(@RequestParam final Long id) {
                return categoryService.getCategoryById(id);

        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @PostMapping(value = RestConstantsUtils.RESOURCE_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "createCategory", description = "createCategory")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<CategoryRestPost> createCategory(@RequestBody final CategoryRestPost category) {
                return categoryService.createCategory(category);

        }

        @Override
        @ResponseStatus(HttpStatus.OK)
        @PutMapping(value = RestConstantsUtils.RESOURCE_CATEGORY)
        @Operation(summary = "updateCategory", description = "updateCategory")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<CategoryRestPost> updateCategory(@RequestBody final CategoryRestPost category) {
                return categoryService.updateCategory(category);
        }

        @Override
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping(value = RestConstantsUtils.RESOURCE_CATEGORY + RestConstantsUtils.RESOURCE_CATEGORYID)
        @Operation(summary = "deleteCategorys", description = "Delte a certain category")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public void deleteCategory(@RequestParam final Long id) {
                categoryService.deleteCategory(id);
        }
}