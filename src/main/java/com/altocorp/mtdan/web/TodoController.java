package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.TodoItem;
import com.altocorp.mtdan.domain.TodoList;
import com.altocorp.mtdan.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    private TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        initializeData(todoRepository);
    }

    private void initializeData(TodoRepository todoRepository) {
        todoRepository.save(new TodoItem("Move TodoRepository to its own package"));
        todoRepository.save(new TodoItem("Add a POST endpoint to be able to create todo items"));
        todoRepository.save(new TodoItem("Hook up JPA to a persistent database"));
        todoRepository.save(new TodoItem("Move TodoItem to an entities package"));
    }

    @RequestMapping("/todos")
    public TodoList todos() {

        List<TodoItem> todoItems = todoRepository.findAll();

        return new TodoList(todoItems);
    }

}
