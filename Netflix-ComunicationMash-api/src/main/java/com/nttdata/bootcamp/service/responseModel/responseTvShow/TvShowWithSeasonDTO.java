package com.nttdata.bootcamp.service.responseModel.responseTvShow;

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
public class TvShowWithSeasonDTO implements Serializable
 {
    @JsonProperty("tvShowId")
    private Long tvShowId;
   
    @JsonProperty("tvShowName")
    private String tvShowName;

    @JsonProperty("seasonNumber")
    private String seasonNumber;

    @JsonProperty("seasonName")
    private String seasonName;

}
