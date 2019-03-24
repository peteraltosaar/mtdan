package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.TodoList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {Application.class})
public class TodoControllerE2E {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void callingTodosEndpoint_returnsTodos() {
        TodoList actual = restTemplate.getForObject("http://localhost:" + port + "/todos", TodoList.class);
        assertEquals(5, actual.getTodoItems().size());
    }

    @Test
    public void callingNewTodoEndpoint_returns200OK() {
        String expectedTodo = "Something";
        ResponseEntity<Object> response = restTemplate.postForEntity("http://localhost:" + port + "do/" + expectedTodo, null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
