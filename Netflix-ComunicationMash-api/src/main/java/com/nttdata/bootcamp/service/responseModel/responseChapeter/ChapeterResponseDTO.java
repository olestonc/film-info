package com.nttdata.bootcamp.service.responseModel.responseChapeter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.bootcamp.persistence.entity.ActorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChapeterResponseDTO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("number")
    private int number;

    @JsonProperty("name")
    private String name;

    @JsonProperty("duration")
    private String duration;

    @JsonProperty("actors")
    private Set<ActorEntity> actors;

}
