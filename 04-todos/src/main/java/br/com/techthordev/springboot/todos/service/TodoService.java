package br.com.techthordev.springboot.todos.service;

import java.util.List;

import br.com.techthordev.springboot.todos.request.TodoRequest;
import br.com.techthordev.springboot.todos.response.TodoResponse;

public interface TodoService {
    
    List<TodoResponse> getAllTodos();
    
    TodoResponse createTodo(TodoRequest todoRequest);
    TodoResponse toggleTodoCompletion(long id);
    
    void deleteTodo(long id);
    
}