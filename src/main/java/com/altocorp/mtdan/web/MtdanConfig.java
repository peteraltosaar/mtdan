package com.altocorp.mtdan.web;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class MtdanConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
