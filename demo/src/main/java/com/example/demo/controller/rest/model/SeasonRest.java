package com.example.demo.controller.rest.model;

import com.example.demo.persistence.entity.ChapeterEntity;
import com.example.demo.persistence.entity.TvShowEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeasonRest implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("number")
    private int number;
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("longDescription")
    private String longDescription;

    @JsonProperty("chapeters")
    private Set<ChapeterEntity> chapeters;

    @JsonProperty("tvShow")
    private TvShowEntity tvShow;

}
