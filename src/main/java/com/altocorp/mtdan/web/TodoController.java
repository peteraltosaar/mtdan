package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.TodoItem;
import com.altocorp.mtdan.domain.TodoList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    private List<TodoItem> todoItems = new ArrayList<>();

    public TodoController() {
        initializeData();
    }

    private void initializeData() {
        todoItems.add(new TodoItem("Move TodoRepository to its own package"));
        todoItems.add(new TodoItem("Add a POST endpoint to be able to create todo items"));
        todoItems.add(new TodoItem("Hook up JPA to a persistent database"));
        todoItems.add(new TodoItem("Move TodoItem to an entities package"));
    }

    @GetMapping(value = "/todos")
    public TodoList todos() {

        return new TodoList(todoItems);
    }

    @PostMapping(value = "/do/{newTodo}")
    public void newTodo(@PathVariable String newTodo) {
        todoItems.add(new TodoItem(newTodo));
    }


}
