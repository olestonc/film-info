package com.nttdata.bootcamp.service.responseModel.reponseActor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("actorId")
    private Long actorId;

    @JsonProperty("actorName")
    private String actorName;

    @JsonProperty("actorDescription")
    private Double actorDescription;

}