package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.Project;
import com.altocorp.mtdan.domain.Todo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TodoService {

    private final RestTemplate restTemplate;
    private final String todoistBearerToken;

    public TodoService(RestTemplate restTemplate, String todoistBearerToken) {
        this.restTemplate = restTemplate;
        this.todoistBearerToken = todoistBearerToken;
    }

    List<Todo> getTodos() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + todoistBearerToken);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<List<Todo>> todosEntity = restTemplate.exchange("https://beta.todoist.com/API/v8/tasks", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Todo>>() {});
        return todosEntity.getBody();
    }

    List<Project> getProjects() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + todoistBearerToken);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<List<Project>> projectsEntity = restTemplate.exchange("https://beta.todoist.com/API/v8/projects", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Project>>() {});
        return projectsEntity.getBody();
    }
}
