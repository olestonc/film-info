package com.example.demo.controller.rest.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.persistence.entity.CategoryEntity;
import com.example.demo.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping(path = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryEntityControllerImpl {

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryEntity>> findAll() {
        try {
            List<CategoryEntity> resultado = new ArrayList<>();

            resultado = categoryService.getAllCategories();

            if (resultado.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CategoryEntity> findById(@PathVariable("id") Long id) {
        Optional<CategoryEntity> categoryEntity = categoryService.getCategoryById(id);

        if (categoryEntity.isPresent()) {
            return new ResponseEntity<>(categoryEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            Optional<CategoryEntity> categoryEntity = categoryService.getCategoryById(id);
            if (categoryEntity.isPresent()) {
                categoryService.deleteCategory((categoryEntity.get().getId()));
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryEntity> update(@PathVariable("id") Long id,
            @RequestBody CategoryEntity categoryEntity) {
        Optional<CategoryEntity> categoryEntityOptional = categoryService.getCategoryById(id);

        if (categoryEntityOptional.isPresent()) {
            CategoryEntity nuevocategoryEntity = categoryEntityOptional.get();
            if (categoryEntity.getName() != null) {
                nuevocategoryEntity.setName(categoryEntity.getName());
            }
            nuevocategoryEntity = categoryService.save(nuevocategoryEntity);
            return new ResponseEntity<>(nuevocategoryEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryEntity> create(@RequestBody CategoryEntity categoryEntity) {// throws 500 internal
                                                                                              // server error if name
                                                                                              // already exist

        CategoryEntity nuevocategoryEntity = categoryService.save(categoryEntity);
        URI uri = createURIcategoryEntity(nuevocategoryEntity);

        return ResponseEntity.created(uri).body(nuevocategoryEntity);

    }

    // Construye la URI del nuevo recurso creado con POST
    private URI createURIcategoryEntity(CategoryEntity categoryEntity) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryEntity.getId())
                .toUri();
    }

}
