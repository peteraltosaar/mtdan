package com.altocorp.mtdan.domain;

import java.util.ArrayList;
import java.util.List;

public class TodoList {

    List<TodoItem> todoItems;

    public TodoList() {
        todoItems = new ArrayList<>();
    }

    public void add(TodoItem todoItem) {
        todoItems.add(todoItem);
    }

    @SuppressWarnings("unused")
    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

}
