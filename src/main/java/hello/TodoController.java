package hello;

import com.altocorp.mtdan.domain.TodoItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @RequestMapping("/")
    public TodoItem todos() {

        return new TodoItem("Test item");
    }

}
