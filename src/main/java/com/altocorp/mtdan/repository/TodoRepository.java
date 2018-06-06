package com.altocorp.mtdan.repository;

import com.altocorp.mtdan.domain.TodoItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<TodoItem, Long> {

    List<TodoItem> findAll();
}
