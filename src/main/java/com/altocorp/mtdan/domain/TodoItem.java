package com.altocorp.mtdan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(schema = "mt_dan", name = "todo_item")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String todo;
    private boolean done;

    public TodoItem(String todo) {

        this.todo = todo;
        this.done = false;
    }

}
