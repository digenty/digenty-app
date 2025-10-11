package com.digenty.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.List;

@Configuration
public class RestClientConfig {
    @Bean
    public RestClient getRestClient() {

        return RestClient.builder()
                .defaultHeaders(e-> {
                    e.setContentType(MediaType.APPLICATION_JSON);
                    e.setAccept(List.of(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML));
                })
                .build();
    }
}
