package com.altocorp.mtdan.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:secrets.properties")
@EnableCaching
public class MtdanConfig {

    @Value("${todoist.bearer.token}")
    public String todoistBearerToken;

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("todoistTasks", "todoistProjects", "todoistLabels");
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8500));
        requestFactory.setProxy(proxy);

        return new RestTemplate(requestFactory);
    }

    @Bean
    public TodoService todoService() {
        return new TodoService(restTemplate(), todoistBearerToken);
    }
}
