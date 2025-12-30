package br.com.techthordev.springboot.todos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.techthordev.springboot.todos.entity.Todo;
import br.com.techthordev.springboot.todos.entity.User;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

	List<Todo> findByOwner(User owner);
	Optional<Todo> findByIdAndOwner(Long id, User owner);
	
}