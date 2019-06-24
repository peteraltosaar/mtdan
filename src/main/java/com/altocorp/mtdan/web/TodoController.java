package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.Project;
import com.altocorp.mtdan.domain.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(value = "/todos")
    public List<Todo> todos() {
        return todoService.getTodos();
    }

    @GetMapping(value = "/projects")
    public List<Project> projects() {
        return todoService.getProjects();
    }
}
