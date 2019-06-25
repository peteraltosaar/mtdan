package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.Todo;
import com.altocorp.mtdan.todoist.TodoistLabel;
import com.altocorp.mtdan.todoist.TodoistProject;
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
    public List<TodoistProject> projects() {
        return todoService.getProjects();
    }

    @GetMapping(value = "/labels")
    public List<TodoistLabel> labels() {
        return todoService.getLabels();
    }
}
