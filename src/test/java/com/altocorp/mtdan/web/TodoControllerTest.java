package com.altocorp.mtdan.web;

import com.altocorp.mtdan.todoist.TodoistLabel;
import com.altocorp.mtdan.todoist.TodoistProject;
import com.altocorp.mtdan.todoist.TodoistTodo;
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

        List<TodoistTodo> expectedTodoistTodos = new ArrayList<>();
        expectedTodoistTodos.add(new TodoistTodo());

        when(todoService.getTodos()).thenReturn(expectedTodoistTodos);

        List<TodoistTodo> actualTodoistTodos = fixture.todos();

        assertThat(actualTodoistTodos).isEqualTo(expectedTodoistTodos);
    }

    @Test
    public void callingProjectsEndpoint_delegatesToTodoServiceForProjects() {

        List<TodoistProject> expectedTodoistProjects = new ArrayList<>();
        expectedTodoistProjects.add(new TodoistProject());

        when(todoService.getProjects()).thenReturn(expectedTodoistProjects);

        List<TodoistProject> actualTodoistProjects = fixture.projects();

        assertThat(actualTodoistProjects).isEqualTo(expectedTodoistProjects);
    }

    @Test
    public void callingLabelsEndpoint_delegatesToTodoServiceForLabels() {

        List<TodoistLabel> expectedTodoistLabels = new ArrayList<>();
        expectedTodoistLabels.add(new TodoistLabel());

        when(todoService.getLabels()).thenReturn(expectedTodoistLabels);

        List<TodoistLabel> actualTodoistLabels = fixture.labels();

        assertThat(actualTodoistLabels).isEqualTo(expectedTodoistLabels);
    }
}