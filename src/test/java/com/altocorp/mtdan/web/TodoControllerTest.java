package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.TodoList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TodoControllerTest {

    private TodoController fixture;

    @Before
    public void setUp() {
        fixture = new TodoController();
    }

    @Test
    public void initializedTodoController_callingTodos_returnsInitializedListOFTodos() {
        TodoList actual = fixture.todos();
        assertEquals(4, actual.getTodoItems().size());
    }

    @Test
    public void callingNewTodo_addsATodo() {
        String expectedTodo = "Do Something";
        fixture.newTodo(expectedTodo);
        TodoList todos = fixture.todos();
        assertEquals(expectedTodo, todos.getTodoItems().get(4).getTodo());
    }
}