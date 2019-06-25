package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.Project;
import com.altocorp.mtdan.domain.Todo;
import com.altocorp.mtdan.todoist.TodoistLabel;
import com.altocorp.mtdan.todoist.TodoistProject;
import com.altocorp.mtdan.todoist.TodoistTodo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TodoService {

    private final RestTemplate restTemplate;
    private final String todoistBearerToken;

    public TodoService(RestTemplate restTemplate, String todoistBearerToken) {
        this.restTemplate = restTemplate;
        this.todoistBearerToken = todoistBearerToken;
    }

    @Cacheable("todoistTasks")
    public List<Todo> getTodos() {
        HttpEntity httpEntity = createTodoistHttpEntity();
        ResponseEntity<List<TodoistTodo>> todosEntity = restTemplate.exchange("https://beta.todoist.com/API/v8/tasks", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistTodo>>() {});
        List<TodoistLabel> todoistLabels = getLabels();
        List<TodoistTodo> todoistTodos = todosEntity.getBody();
        return createDomainTodos(todoistTodos, todoistLabels);
    }

    @Cacheable("todoistProjects")
    public List<Project> getProjects() {
        HttpEntity httpEntity = createTodoistHttpEntity();
        ResponseEntity<List<TodoistProject>> projectsEntity = restTemplate.exchange("https://beta.todoist.com/API/v8/projects", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistProject>>() {});
        List<TodoistProject> todoistProjects = projectsEntity.getBody();
        return createDomainProjects(todoistProjects);
    }

    @Cacheable("todoistLabels")
    public List<TodoistLabel> getLabels() {
        HttpEntity httpEntity = createTodoistHttpEntity();
        ResponseEntity<List<TodoistLabel>> projectsEntity = restTemplate.exchange("https://beta.todoist.com/API/v8/labels", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistLabel>>() {});
        return projectsEntity.getBody();
    }

    private HttpEntity createTodoistHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + todoistBearerToken);
        return new HttpEntity(httpHeaders);
    }

    private List<Todo> createDomainTodos(List<TodoistTodo> todoistTodos, List<TodoistLabel> todoistLabels) {
        Map<Long, String> mapOfLabelIdsToLabelNames = createMapOfLabelIdsToLabelNames(todoistLabels);

        List<Todo> domainTodos = new ArrayList<>();
        for (TodoistTodo todoistTodo : todoistTodos) {
            Todo domainTodo = new Todo();
            domainTodo.setId(todoistTodo.getId());
            domainTodo.setProjectId(todoistTodo.getProjectId());
            domainTodo.setContent(todoistTodo.getContent());
            List<String> labels = new ArrayList<>();
            for (Long id : todoistTodo.getLabelIds()) {
                labels.add(mapOfLabelIdsToLabelNames.get(id));
            }
            domainTodo.setLabels(labels);
            domainTodo.setOrder(todoistTodo.getOrder());
            domainTodo.setIndent(todoistTodo.getIndent());
            domainTodo.setPriority(todoistTodo.getPriority());
            domainTodo.setCommentCount(todoistTodo.getCommentCount());
            domainTodo.setCreated(todoistTodo.getCreated());
            domainTodo.setUrl(todoistTodo.getUrl());
            domainTodos.add(domainTodo);
        }
        return domainTodos;
    }

    private Map<Long, String> createMapOfLabelIdsToLabelNames(List<TodoistLabel> todoistLabels) {
        return todoistLabels.stream().collect(Collectors.toMap(TodoistLabel::getId, TodoistLabel::getName, (a, b) -> b));
    }

    private List<Project> createDomainProjects(List<TodoistProject> todoistProjects) {
        List<Project> domainProjects = new ArrayList<>();
        for (TodoistProject todoistProject : todoistProjects) {
            Project domainProject = new Project();
            domainProject.setId(todoistProject.getId());
            domainProject.setName(todoistProject.getName());
            domainProject.setOrder(todoistProject.getOrder());
            domainProject.setIndent(todoistProject.getIndent());
            domainProjects.add(domainProject);
        }
        return domainProjects;
    }
}
