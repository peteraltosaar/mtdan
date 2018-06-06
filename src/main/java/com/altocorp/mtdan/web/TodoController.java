package com.altocorp.mtdan.web;

import com.altocorp.mtdan.domain.TodoItem;
import com.altocorp.mtdan.domain.TodoList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    public TodoController() {

    }

    @RequestMapping("/todos")
    public TodoList todos() {

        TodoList todoList = new TodoList();
        todoList.add(new TodoItem("Todo Item 1"));
        todoList.add(new TodoItem("Todo Item 2"));
        todoList.add(new TodoItem("Todo Item 3"));
        todoList.add(new TodoItem("Todo Item 4"));
        todoList.add(new TodoItem("DevOps COE"));

        return todoList;
    }

}
