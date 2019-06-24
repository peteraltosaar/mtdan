package com.altocorp.mtdan.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:secrets.properties")
public class MtdanConfig {

    @Value("${todoist.bearer.token}")
    public String todoistBearerToken;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public TodoService todoService() {
        return new TodoService(restTemplate(), todoistBearerToken);
    }
}
