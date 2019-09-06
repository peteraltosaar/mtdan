package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.Project;
import com.altocorp.mtdan.domain.Todo;
import com.altocorp.mtdan.todoist.TodoistLabel;
import com.altocorp.mtdan.todoist.TodoistProject;
import com.altocorp.mtdan.todoist.TodoistTodo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TodoServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private TodoService fixture;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        String EXPECTED_BEARER_TOKEN = "AAABBBCCCDDD";
        fixture = new TodoService(restTemplate, EXPECTED_BEARER_TOKEN);
    }

    @Test
    public void callingGetTodos_callsRestTemplateForTodos() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer AAABBBCCCDDD");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        List<TodoistTodo> expectedTodoistTodos = new ArrayList<>();
        TodoistTodo todoistTodo = new TodoistTodo();
        todoistTodo.setProjectId("50");
        List<Long> labelIds = new ArrayList<>();
        labelIds.add(1L);
        todoistTodo.setLabelIds(labelIds);
        expectedTodoistTodos.add(todoistTodo);

        ResponseEntity<List<TodoistTodo>> expectedTasksResponseEntity = new ResponseEntity<>(expectedTodoistTodos, HttpStatus.OK);
        when(restTemplate.exchange("https://api.todoist.com/rest/v1/tasks", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistTodo>>() {})).thenReturn(expectedTasksResponseEntity);

        List<TodoistProject> expectedTodoistProjects = new ArrayList<>();
        TodoistProject todoistProject = new TodoistProject();
        todoistProject.setId(50);
        todoistProject.setName("some_project");
        todoistProject.setOrder(1);
        todoistProject.setIndent(1);
        todoistProject.setCommentCount(0);
        expectedTodoistProjects.add(todoistProject);

        ResponseEntity<List<TodoistProject>> expectedProjectsResponseEntity = new ResponseEntity<>(expectedTodoistProjects, HttpStatus.OK);
        when(restTemplate.exchange("https://api.todoist.com/rest/v1/projects", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistProject>>() {})).thenReturn(expectedProjectsResponseEntity);

        List<TodoistLabel> expectedTodoistLabels = new ArrayList<>();
        TodoistLabel todoistLabel = new TodoistLabel();
        todoistLabel.setId(1);
        todoistLabel.setName("some_label");
        todoistLabel.setOrder(1);
        expectedTodoistLabels.add(todoistLabel);
        ResponseEntity<List<TodoistLabel>> expectedLabelsResponseEntity = new ResponseEntity<>(expectedTodoistLabels, HttpStatus.OK);
        when(restTemplate.exchange("https://api.todoist.com/rest/v1/labels", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistLabel>>() {})).thenReturn(expectedLabelsResponseEntity);

        List<Todo> actualTodos = fixture.getTodos();

        List<Todo> expectedTodos = new ArrayList<>();
        Todo expectedTodo = new Todo();
        List<String> expectedLabels = new ArrayList<>();
        expectedLabels.add("some_label");
        expectedTodo.setLabels(expectedLabels);
        expectedTodo.setProject("some_project");
        expectedTodos.add(expectedTodo);
        assertThat(actualTodos).isEqualTo(expectedTodos);
    }

    @Test
    public void callingGetProjects_callsRestTemplateForProjects() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer AAABBBCCCDDD");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        List<TodoistProject> expectedTodoistProjects = new ArrayList<>();
        expectedTodoistProjects.add(new TodoistProject());

        ResponseEntity<List<TodoistProject>> expectedResponseEntity = new ResponseEntity<>(expectedTodoistProjects, HttpStatus.OK);
        when(restTemplate.exchange("https://api.todoist.com/rest/v1/projects", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistProject>>() {})).thenReturn(expectedResponseEntity);

        List<Project> actualProjects = fixture.getProjects();

        List<Project> expectedProjects = new ArrayList<>();
        expectedProjects.add(new Project());

        assertThat(actualProjects).isEqualTo(expectedProjects);
    }

    @Test
    public void callingGetLabels_callsRestTemplateForLabels() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer AAABBBCCCDDD");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        List<TodoistLabel> expectedTodoistLabels = new ArrayList<>();
        expectedTodoistLabels.add(new TodoistLabel());

        ResponseEntity<List<TodoistLabel>> expectedResponseEntity = new ResponseEntity<>(expectedTodoistLabels, HttpStatus.OK);
        when(restTemplate.exchange("https://api.todoist.com/rest/v1/labels", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistLabel>>() {})).thenReturn(expectedResponseEntity);

        List<TodoistLabel> actualTodoistLabels = fixture.getLabels();

        assertThat(actualTodoistLabels).isEqualTo(expectedTodoistLabels);
    }
}