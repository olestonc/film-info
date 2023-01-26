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

import com.example.demo.persistence.entity.ChapeterEntity;
import com.example.demo.service.impl.ChapeterServiceImpl;

@RestController
@RequestMapping(path = "/chapeter", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChapeterEntityControllerImpl {

    @Autowired
    ChapeterServiceImpl chapeterService;

    @GetMapping()
    public ResponseEntity<List<ChapeterEntity>> findAll() {
        try {
            List<ChapeterEntity> resultado = new ArrayList<>();

            resultado = chapeterService.getAllChapeters();

            if (resultado.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ChapeterEntity> findById(@PathVariable("id") Long id) {
        Optional<ChapeterEntity> chapeterEntity = chapeterService.getChapeterById(id);

        if (chapeterEntity.isPresent()) {
            return new ResponseEntity<>(chapeterEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            Optional<ChapeterEntity> chapeterEntity = chapeterService.getChapeterById(id);
            if (chapeterEntity.isPresent()) {
                chapeterService.deleteChapeter((chapeterEntity.get().getId()));
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChapeterEntity> update(@PathVariable("id") Long id,
            @RequestBody ChapeterEntity chapeterEntity) {
        Optional<ChapeterEntity> chapeterEntityOptional = chapeterService.getChapeterById(id);

        if (chapeterEntityOptional.isPresent()) {
            ChapeterEntity nuevochapeterEntity = chapeterEntityOptional.get();
            if (chapeterEntity.getName() != null) {
                nuevochapeterEntity.setName(chapeterEntity.getName());
            }
            if (chapeterEntity.getDuration() > 0) {
                nuevochapeterEntity.setDuration(chapeterEntity.getDuration());
            }
            if (chapeterEntity.getNumber() > 0) {
                nuevochapeterEntity.setNumber(chapeterEntity.getNumber());
            }
            if (chapeterEntity.getActors() != null && !chapeterEntity.getActors().isEmpty()) {
                nuevochapeterEntity.setActors(chapeterEntity.getActors());
            }
            nuevochapeterEntity = chapeterService.save(nuevochapeterEntity);
            return new ResponseEntity<>(nuevochapeterEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChapeterEntity> create(@RequestBody ChapeterEntity chapeterEntity) {

        ChapeterEntity nuevochapeterEntity = chapeterService.save(chapeterEntity);
        URI uri = createURIchapeterEntity(nuevochapeterEntity);

        return ResponseEntity.created(uri).body(nuevochapeterEntity);

    }

    // Construye la URI del nuevo recurso creado con POST
    private URI createURIchapeterEntity(ChapeterEntity chapeterEntity) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(chapeterEntity.getId())
                .toUri();
    }

}
