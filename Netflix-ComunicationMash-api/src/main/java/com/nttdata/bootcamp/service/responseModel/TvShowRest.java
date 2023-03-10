package com.nttdata.bootcamp.service.responseModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.bootcamp.persistence.entity.CategoryEntity;
import com.nttdata.bootcamp.persistence.entity.SeasonEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TvShowRest implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortDescription")
    private String shortDescription;

    @JsonProperty("longDescription")
    private String longDescription;

    @JsonProperty("year")
    private int year;

    @JsonProperty("recommendedAge")
    private int recommendedAge;

    @JsonProperty("advertising")
    private boolean advertising;

    @JsonProperty("category")
    private CategoryEntity category;

    @JsonProperty("seasons")
    private Set<SeasonEntity> seasons;
}
