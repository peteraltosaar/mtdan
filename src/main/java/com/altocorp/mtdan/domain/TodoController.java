package com.altocorp.mtdan.domain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @SuppressWarnings("unused")
    @RequestMapping("/todos")
    public TodoList todos() {

//        TodoList todoList = new TodoList();
//
//        List<TodoItem> allTodoItems = todoRepository.findAll();
//        for (TodoItem todoItem : allTodoItems) {
//            todoList.add(todoItem);
//        }
//
//        return todoList;
        TodoList todoList = new TodoList();
        todoList.add(new TodoItem("Todo Item 1"));
        todoList.add(new TodoItem("Todo Item 2"));
        todoList.add(new TodoItem("Todo Item 3"));
        todoList.add(new TodoItem("Todo Item 4"));
        todoList.add(new TodoItem("DevOps COE"));

        return todoList;
    }

}
