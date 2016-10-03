package com.altocorp.mtdan.domain;

import lombok.Data;

@Data
public class TodoItem {

    private String todo;
    private boolean complete;

    public TodoItem(String todo) {
        this.todo = todo;
        this.complete = false;
    }

}
