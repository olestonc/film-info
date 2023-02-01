package com.nttdata.bootcamp.exception.error;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spotify.error")
@Data
public class ErrorMessageService {

    @NonNull
    private Map<String, String> codes;

}
