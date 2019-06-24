package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.Label;
import com.altocorp.mtdan.domain.Project;
import com.altocorp.mtdan.domain.Todo;
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

        List<Todo> expectedTodos = new ArrayList<>();
        expectedTodos.add(new Todo());

        ResponseEntity<List<Todo>> expectedResponseEntity = new ResponseEntity<>(expectedTodos, HttpStatus.OK);
        when(restTemplate.exchange("https://beta.todoist.com/API/v8/tasks", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Todo>>() {})).thenReturn(expectedResponseEntity);

        List<Todo> actualTodos = fixture.getTodos();

        assertThat(actualTodos).isEqualTo(expectedTodos);
    }

    @Test
    public void callingGetProjects_callsRestTemplateForProjects() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer AAABBBCCCDDD");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        List<Project> expectedProjects = new ArrayList<>();
        expectedProjects.add(new Project());

        ResponseEntity<List<Project>> expectedResponseEntity = new ResponseEntity<>(expectedProjects, HttpStatus.OK);
        when(restTemplate.exchange("https://beta.todoist.com/API/v8/projects", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Project>>() {})).thenReturn(expectedResponseEntity);

        List<Project> actualProjects = fixture.getProjects();

        assertThat(actualProjects).isEqualTo(expectedProjects);
    }

    @Test
    public void callingGetLabels_callsRestTemplateForLabels() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer AAABBBCCCDDD");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        List<Label> expectedLabels = new ArrayList<>();
        expectedLabels.add(new Label());

        ResponseEntity<List<Label>> expectedResponseEntity = new ResponseEntity<>(expectedLabels, HttpStatus.OK);
        when(restTemplate.exchange("https://beta.todoist.com/API/v8/labels", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Label>>() {})).thenReturn(expectedResponseEntity);

        List<Label> actualLabels = fixture.getLabels();

        assertThat(actualLabels).isEqualTo(expectedLabels);
    }
}