package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.Label;
import com.altocorp.mtdan.domain.Project;
import com.altocorp.mtdan.domain.Todo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class TodoControllerTest {

    private TodoController fixture;

    @Mock
    private TodoService todoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        fixture = new TodoController(todoService);
    }

    @Test
    public void callingTodosEndpoint_delegatesToTodoServiceForTodos() {

        List<Todo> expectedTodos = new ArrayList<>();
        expectedTodos.add(new Todo());

        when(todoService.getTodos()).thenReturn(expectedTodos);

        List<Todo> actualTodos = fixture.todos();

        assertThat(actualTodos).isEqualTo(expectedTodos);
    }

    @Test
    public void callingProjectsEndpoint_delegatesToTodoServiceForProjects() {

        List<Project> expectedProjects = new ArrayList<>();
        expectedProjects.add(new Project());

        when(todoService.getProjects()).thenReturn(expectedProjects);

        List<Project> actualProjects = fixture.projects();

        assertThat(actualProjects).isEqualTo(expectedProjects);
    }

    @Test
    public void callingLabelsEndpoint_delegatesToTodoServiceForLabels() {

        List<Label> expectedLabels = new ArrayList<>();
        expectedLabels.add(new Label());

        when(todoService.getLabels()).thenReturn(expectedLabels);

        List<Label> actualLabels = fixture.labels();

        assertThat(actualLabels).isEqualTo(expectedLabels);
    }
}