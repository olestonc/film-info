package com.nttdata.bootcamp.service.responseModel.responseChapeter;

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
public class ChapeterResponseDTO implements Serializable {

    @JsonProperty("chapeterId")
    private Long chapeterId;

    @JsonProperty("chapeterNumber")
    private int chapeterNumber;

    @JsonProperty("chapeterName")
    private String chapeterName;

    @JsonProperty("chapeterDuration")
    private String chapeterDuration;

}
