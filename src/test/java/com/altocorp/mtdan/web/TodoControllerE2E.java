package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.Project;
import com.altocorp.mtdan.domain.Todo;
import com.altocorp.mtdan.todoist.TodoistLabel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SuppressWarnings("ConstantConditions")
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
    public void callingTodosEndpoint_delegatesToTodosServiceToReturnTodos() {
        ResponseEntity<List<Todo>> todosEntity = restTemplate.exchange("http://localhost:" + port + "/todos", HttpMethod.GET, null, new ParameterizedTypeReference<List<Todo>>() {});
        List<Todo> todos = todosEntity.getBody();
        assertThat(todos.size()).isGreaterThan(0);
    }

    @Test
    public void callingProjectsEndpoint_delegatesToTodosServiceToReturnProjects() {
        ResponseEntity<List<Project>> projectsEntity = restTemplate.exchange("http://localhost:" + port + "/projects", HttpMethod.GET, null, new ParameterizedTypeReference<List<Project>>() {});
        List<Project> projects = projectsEntity.getBody();
        assertThat(projects.size()).isGreaterThan(0);
    }

    @Test
    public void callingLabelsEndpoint_delegatesToTodosServiceToReturnLabels() {
        ResponseEntity<List<TodoistLabel>> projectsEntity = restTemplate.exchange("http://localhost:" + port + "/labels", HttpMethod.GET, null, new ParameterizedTypeReference<List<TodoistLabel>>() {});
        List<TodoistLabel> projects = projectsEntity.getBody();
        assertThat(projects.size()).isEqualTo(0);
    }
}
