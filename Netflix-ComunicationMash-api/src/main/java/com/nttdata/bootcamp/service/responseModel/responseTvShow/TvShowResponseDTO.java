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
public class TvShowResponseDTO implements Serializable {

    @JsonProperty("tvShowId")
    private Long tvShowId;

    @JsonProperty("tvShowName")
    private String tvShowName;

    @JsonProperty("tvShowShortDescription")
    private String tvShowShortDescription;

    @JsonProperty("tvShowLongDescription")
    private String tvShowLongDescription;

    @JsonProperty("tvShowYear")
    private int tvShowYear;

    @JsonProperty("tvShowRecommendedAge")
    private int tvShowRecommendedAge;

    @JsonProperty("tvShowAdvertising")
    private boolean tvShowAdvertising;

}
