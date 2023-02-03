package com.nttdata.bootcamp.service.responseModel.reponseActor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.bootcamp.persistence.entity.ChapeterEntity;
import com.nttdata.bootcamp.persistence.entity.TvShowEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorWithChapetersResponseDTO implements Serializable {// Otro nombre posible sería ActorDetails[...]

    private static final long serialVersionUID = 1L;

    @JsonProperty("actorId")
    private Long actorId;

    @JsonProperty("actorName")
    private String actorName;

    @JsonProperty("actorDescription")
    private Double actorDescription;

    @JsonProperty("actorChapeters")
    private Set<ChapeterEntity> actorChapeters;

    @JsonProperty("actorTvShows")
    private Set<TvShowEntity> actorTvShows;

}