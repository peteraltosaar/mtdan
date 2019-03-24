package com.altocorp.mtdan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
public class TodoItem {

    private Long id;
    private String todo;
    private boolean done;

    protected TodoItem() {}

    public TodoItem(String todo) {

        this.todo = todo;
        this.done = false;
    }

}
