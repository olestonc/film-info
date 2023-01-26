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

import com.example.demo.persistence.entity.TvShowEntity;
import com.example.demo.service.impl.TvShowServiceImpl;

@RestController
@RequestMapping(path = "/tvshow", produces = MediaType.APPLICATION_JSON_VALUE)
public class TvShowEntityControllerImpl {

    @Autowired
    TvShowServiceImpl tvshowService;

    @GetMapping()
    public ResponseEntity<List<TvShowEntity>> findAllTvShow() {
        try {
            List<TvShowEntity> resultado = new ArrayList<>();

            resultado = tvshowService.getAllTvShows();

            if (resultado.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<TvShowEntity> findTvShowById(@PathVariable("id") Long id) {
        Optional<TvShowEntity> tvshowEntity = tvshowService.getTvShowById(id);

        if (tvshowEntity.isPresent()) {
            return new ResponseEntity<>(tvshowEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteTvShow(@PathVariable("id") Long id) {
        try {
            Optional<TvShowEntity> tvshowEntity = tvshowService.getTvShowById(id);
            if (tvshowEntity.isPresent()) {
                tvshowService.deleteTvShow((tvshowEntity.get().getId()));
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TvShowEntity> updateTvShow(@PathVariable("id") Long id,
            @RequestBody TvShowEntity tvShowEntity) {
        Optional<TvShowEntity> tvshowEntityOptional = tvshowService.getTvShowById(id);

        if (tvshowEntityOptional.isPresent()) {
            TvShowEntity nuevotvshowEntity = tvshowEntityOptional.get();
            if (tvShowEntity.getName() != null) {
                nuevotvshowEntity.setName(tvShowEntity.getName());
            }
            if (tvShowEntity.getYear() > 0) {
                nuevotvshowEntity.setYear(tvShowEntity.getYear());
            }
            if (tvShowEntity.getRecommendedAge() > 0) {
                nuevotvshowEntity.setRecommendedAge(tvShowEntity.getRecommendedAge());
            }
            if (tvShowEntity.()) {
                nuevotvshowEntity.setAdvertising(tvShowEntity.isAdvertising());
            }
            if (tvShowEntity.getLongDescription() != null && tvShowEntity.getLongDescription().trim().length() > 0) {
                nuevotvshowEntity.setLongDescription(tvShowEntity.getLongDescription());
            }
            if (tvShowEntity.getShortDescription() != null && tvShowEntity.getShortDescription().trim().length() > 0) {
                nuevotvshowEntity.setShortDescription(tvShowEntity.getShortDescription());
            }
            if (tvShowEntity.getCategory() != null) {
                nuevotvshowEntity.setSeasons(tvShowEntity.getSeasons());
            }
            if (tvShowEntity.getSeasons() != null && (!tvShowEntity.getSeasons().isEmpty())) {
                nuevotvshowEntity.setSeasons(tvShowEntity.getSeasons());
            }
            nuevotvshowEntity = tvshowService.save(nuevotvshowEntity);
            return new ResponseEntity<>(nuevotvshowEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TvShowEntity> createTvShow(@RequestBody TvShowEntity tvshowEntity) {

        TvShowEntity nuevotvshowEntity = tvshowService.save(tvshowEntity);
        URI uri = createURItvshowEntity(nuevotvshowEntity);

        return ResponseEntity.created(uri).body(nuevotvshowEntity);
    }

    // Construye la URI del nuevo recurso creado con POST
    private URI createURItvshowEntity(TvShowEntity tvshowEntity) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tvshowEntity.getId())
                .toUri();
    }

}
