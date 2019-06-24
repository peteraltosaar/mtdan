package com.altocorp.mtdan.web;

import com.altocorp.mtdan.todoist.TodoistLabel;
import com.altocorp.mtdan.todoist.TodoistProject;
import com.altocorp.mtdan.todoist.TodoistTodo;
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

    List<TodoistTodo> getTodos() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + todoistBearerToken);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<List<TodoistTodo>> todosEntity = restTemplate.exchange("https://beta.todoist.com/API/v8/tasks", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistTodo>>() {});
        return todosEntity.getBody();
    }

    List<TodoistProject> getProjects() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + todoistBearerToken);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<List<TodoistProject>> projectsEntity = restTemplate.exchange("https://beta.todoist.com/API/v8/projects", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistProject>>() {});
        return projectsEntity.getBody();
    }

    List<TodoistLabel> getLabels() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + todoistBearerToken);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<List<TodoistLabel>> projectsEntity = restTemplate.exchange("https://beta.todoist.com/API/v8/labels", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistLabel>>() {});
        return projectsEntity.getBody();
    }
}
