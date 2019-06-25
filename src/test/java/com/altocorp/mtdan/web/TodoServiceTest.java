package com.altocorp.mtdan.web;

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
        List<Long> labelIds = new ArrayList<>();
        labelIds.add(1L);
        todoistTodo.setLabelIds(labelIds);
        expectedTodoistTodos.add(todoistTodo);

        ResponseEntity<List<TodoistTodo>> expectedTasksResponseEntity = new ResponseEntity<>(expectedTodoistTodos, HttpStatus.OK);
        when(restTemplate.exchange("https://beta.todoist.com/API/v8/tasks", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistTodo>>() {})).thenReturn(expectedTasksResponseEntity);

        List<TodoistLabel> expectedTodoistLabels = new ArrayList<>();
        TodoistLabel todoistLabel = new TodoistLabel();
        todoistLabel.setId(1);
        todoistLabel.setName("some_label");
        todoistLabel.setOrder(1);
        expectedTodoistLabels.add(todoistLabel);
        ResponseEntity<List<TodoistLabel>> expectedLabelsResponseEntity = new ResponseEntity<>(expectedTodoistLabels, HttpStatus.OK);
        when(restTemplate.exchange("https://beta.todoist.com/API/v8/labels", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistLabel>>() {})).thenReturn(expectedLabelsResponseEntity);

        List<Todo> actualTodos = fixture.getTodos();

        // Fix this assertion to be on the object as a whole, once it has an Equals implementation
        Todo actualTodo = actualTodos.get(0);
        assertThat(actualTodo.getLabels().get(0)).isEqualTo("some_label");
    }

    @Test
    public void callingGetProjects_callsRestTemplateForProjects() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer AAABBBCCCDDD");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        List<TodoistProject> expectedTodoistProjects = new ArrayList<>();
        expectedTodoistProjects.add(new TodoistProject());

        ResponseEntity<List<TodoistProject>> expectedResponseEntity = new ResponseEntity<>(expectedTodoistProjects, HttpStatus.OK);
        when(restTemplate.exchange("https://beta.todoist.com/API/v8/projects", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistProject>>() {})).thenReturn(expectedResponseEntity);

        List<TodoistProject> actualTodoistProjects = fixture.getProjects();

        assertThat(actualTodoistProjects).isEqualTo(expectedTodoistProjects);
    }

    @Test
    public void callingGetLabels_callsRestTemplateForLabels() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer AAABBBCCCDDD");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        List<TodoistLabel> expectedTodoistLabels = new ArrayList<>();
        expectedTodoistLabels.add(new TodoistLabel());

        ResponseEntity<List<TodoistLabel>> expectedResponseEntity = new ResponseEntity<>(expectedTodoistLabels, HttpStatus.OK);
        when(restTemplate.exchange("https://beta.todoist.com/API/v8/labels", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoistLabel>>() {})).thenReturn(expectedResponseEntity);

        List<TodoistLabel> actualTodoistLabels = fixture.getLabels();

        assertThat(actualTodoistLabels).isEqualTo(expectedTodoistLabels);
    }
}