package com.altocorp.mtdan.domain;

import com.altocorp.mtdan.domain.TodoItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<TodoItem, Long> {

    List<TodoItem> findAll();
}
