package com.nttdata.bootcamp.service.responseModel.responseSeason;

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
public class SeasonResponseDTO implements Serializable {

    @JsonProperty("seasonId")
    private Long seasonId;

    @JsonProperty("seasonNumber")
    private int seasonNumber;

    @JsonProperty("seasonName")
    private String seasonName;

}
