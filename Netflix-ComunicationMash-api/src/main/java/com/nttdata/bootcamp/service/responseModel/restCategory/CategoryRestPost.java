package com.nttdata.bootcamp.service.responseModel.restCategory;

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
public class CategoryRestPost implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;


}
