package com.nttdata.bootcamp.ws.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.service.CategoryService;
import com.nttdata.bootcamp.service.responseModel.D4iPageRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.service.responseModel.responseCategory.CategoryResponseDTO;
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
public class CategoryController {

        private final CategoryService categoryService;

        @GetMapping(value = RestConstantsUtils.RESOURCE_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getAllCategorys", description = "Get all Categorys paginated")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<D4iPageRest<CategoryResponseDTO>> getAllCategories(
                        @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
                        @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
                        @Parameter(hidden = true) final Pageable pageable) {

                return categoryService.getAllCategorys(pageable);
        }

        @GetMapping(value = RestConstantsUtils.RESOURCE_CATEGORY
                        + RestConstantsUtils.RESOURCE_CATEGORYID, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "getCategoryById", description = "Get a category by its id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<CategoryResponseDTO> getCategoryById(@RequestParam final Long id) {
                return categoryService.getCategoryById(id);

        }

        @PostMapping(value = RestConstantsUtils.RESOURCE_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
        @Operation(summary = "createCategory", description = "createCategory")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<CategoryResponseDTO> createCategory(@RequestBody final CategoryResponseDTO category) {
                return categoryService.createCategory(category);

        }

        @PutMapping(value = RestConstantsUtils.RESOURCE_CATEGORY)
        @Operation(summary = "updateCategory", description = "updateCategory")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<CategoryResponseDTO> updateCategory(@RequestBody final CategoryResponseDTO category) {
                return categoryService.updateCategory(category);
        }

        @DeleteMapping(value = RestConstantsUtils.RESOURCE_CATEGORY + RestConstantsUtils.RESOURCE_CATEGORYID)
        @Operation(summary = "deleteCategorys", description = "Delte a certain category")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        })
        public NetflixResponse<CategoryResponseDTO> deleteCategory(@RequestParam final Long id) {
                return categoryService.deleteCategory(id);
        }
}
