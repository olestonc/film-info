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

import com.example.demo.persistence.entity.SeasonEntity;
import com.example.demo.service.impl.SeasonServiceImpl;

@RestController
@RequestMapping(path = "/season", produces = MediaType.APPLICATION_JSON_VALUE)
public class SeasonEntityControllerImpl {

    @Autowired
    SeasonServiceImpl seasonService;

    @GetMapping()
    public ResponseEntity<List<SeasonEntity>> findAllSeason() {
        try {
            List<SeasonEntity> resultado = new ArrayList<>();

            resultado = seasonService.getAllSeasons();

            if (resultado.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<SeasonEntity> findSeasonById(@PathVariable("id") Long id) {
        Optional<SeasonEntity> seasonEntity = seasonService.getSeasonById(id);

        if (seasonEntity.isPresent()) {
            return new ResponseEntity<>(seasonEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteSeason(@PathVariable("id") Long id) {
        try {
            Optional<SeasonEntity> seasonEntity = seasonService.getSeasonById(id);
            if (seasonEntity.isPresent()) {
                seasonService.deleteSeason((seasonEntity.get().getId()));
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SeasonEntity> updateSeason(@PathVariable("id") Long id,
            @RequestBody SeasonEntity seasonEntity) {
        Optional<SeasonEntity> seasonEntityOptional = seasonService.getSeasonById(id);

        if (seasonEntityOptional.isPresent()) {
            SeasonEntity nuevoseasonEntity = seasonEntityOptional.get();
            if (seasonEntity.getName() != null) {
                nuevoseasonEntity.setName(seasonEntity.getName());
            }
            if (seasonEntity.getLongDescription() != null && seasonEntity.getLongDescription().trim().length() > 0) {
                nuevoseasonEntity.setLongDescription(seasonEntity.getLongDescription());
            }
            if (seasonEntity.getNumber() > 0) {
                nuevoseasonEntity.setNumber(seasonEntity.getNumber());
            }
            if (seasonEntity.getChapeters() != null && (!seasonEntity.getChapeters().isEmpty())) {
                nuevoseasonEntity.setChapeters(seasonEntity.getChapeters());
            }
            nuevoseasonEntity = seasonService.save(nuevoseasonEntity);
            return new ResponseEntity<>(nuevoseasonEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SeasonEntity> createSeason(@RequestBody SeasonEntity seasonEntity) {

        SeasonEntity nuevoseasonEntity = seasonService.save(seasonEntity);
        URI uri = createURIseasonEntity(nuevoseasonEntity);

        return ResponseEntity.created(uri).body(nuevoseasonEntity);

    }

    // Construye la URI del nuevo recurso creado con POST
    private URI createURIseasonEntity(SeasonEntity seasonEntity) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(seasonEntity.getId())
                .toUri();
    }

}
