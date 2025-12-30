package br.com.techthordev.springboot.todos.controller;

import br.com.techthordev.springboot.todos.request.TodoRequest;
import br.com.techthordev.springboot.todos.response.TodoResponse;
import br.com.techthordev.springboot.todos.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Todo REST API Endpoints",
    description = "Operations for managing user todos"
)
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(
        summary = "Get all todos for user",
        description = "Fetch all todo from signed in user"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<TodoResponse> getAllTodos() {
        return todoService.getAllTodos();
    }

    @Operation(
        summary = "Create todo for user",
        description = "Create todo for the signed in user"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TodoResponse createTodo(
        @Valid @RequestBody TodoRequest todoRequest
    ) {
        return todoService.createTodo(todoRequest);
    }

    @Operation(
        summary = "Update todo for user",
        description = "Update todo for the signed in user"
    )
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public TodoResponse toggleTodoCompletion(@PathVariable @Min(1) long id) {
        return todoService.toggleTodoCompletion(id);
    }

    @Operation(
        summary = "Delete todo for user",
        description = "Delete todo for the signed in user"
    )
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable @Min(1) long id) {
        todoService.deleteTodo(id);
    }
    
}
