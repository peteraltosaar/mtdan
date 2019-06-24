package com.altocorp.mtdan.web;

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
    public void callingGetTodosCallsRestTemplateForTodos() {

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
}