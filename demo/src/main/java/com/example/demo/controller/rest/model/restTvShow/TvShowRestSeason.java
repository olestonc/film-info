package com.example.demo.controller.rest.model.restTvShow;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TvShowRestSeason implements Serializable
 {

    // id, number and name of the season and the tv show
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("seasonNumber")
    private String seasonNumber;

    @JsonProperty("seasonName")
    private String seasonName;

    @JsonProperty("name")
    private String name;
}
