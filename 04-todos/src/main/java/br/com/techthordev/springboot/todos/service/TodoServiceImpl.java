package br.com.techthordev.springboot.todos.service;

import br.com.techthordev.springboot.todos.entity.Todo;
import br.com.techthordev.springboot.todos.entity.User;
import br.com.techthordev.springboot.todos.repository.TodoRepository;
import br.com.techthordev.springboot.todos.request.TodoRequest;
import br.com.techthordev.springboot.todos.response.TodoResponse;
import br.com.techthordev.springboot.todos.util.FindAuthenticatedUser;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private final FindAuthenticatedUser findAuthenticatedUser;

    public TodoServiceImpl(
        TodoRepository todoRepository,
        FindAuthenticatedUser findAuthenticatedUser
    ) {
        this.todoRepository = todoRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }

    @Override
    @Transactional
    public TodoResponse createTodo(TodoRequest todoRequest) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        Todo todo = new Todo(
            todoRequest.getTitle(),
            todoRequest.getDescription(),
            todoRequest.getPriority(),
            false,
            currentUser
        );

        Todo savedTodo = todoRepository.save(todo);

        return convertToTodoResponse(savedTodo);
    }

    private TodoResponse convertToTodoResponse(Todo todo) {
        return new TodoResponse(
            todo.getId(),
            todo.getTitle(),
            todo.getDescription(),
            todo.getPriority(),
            todo.isCompoete()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoResponse> getAllTodos() {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        return todoRepository
            .findByOwner(currentUser)
            .stream()
            .map(this::convertToTodoResponse)
            .toList();
    }

    @Override
    @Transactional
    public TodoResponse toggleTodoCompletion(long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        Optional<Todo> todo = todoRepository.findByIdAndOwner(id, currentUser);

        if (todo.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Todo not found"
            );
        }

        todo.get().setCompoete(!todo.get().isCompoete());
        Todo updatedTodo = todoRepository.save(todo.get());

        return convertToTodoResponse(updatedTodo);
    }

    @Transactional
    public void deleteTodo(long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        Optional<Todo> todo = todoRepository.findByIdAndOwner(id, currentUser);

        if (todo.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Todo not found"
            );
        }
        todoRepository.delete(todo.get());
    }
}
