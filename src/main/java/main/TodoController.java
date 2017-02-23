package main;

import com.altocorp.mtdan.domain.TodoItem;
import com.altocorp.mtdan.domain.TodoList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @SuppressWarnings("unused")
    @RequestMapping("/")
    public TodoList todos() {

        TodoList todoList = new TodoList();
        todoList.add(new TodoItem("Todo Item 1"));
        todoList.add(new TodoItem("Todo Item 2"));

        return todoList;
    }

}
