package br.com.techthordev.springboot.todos.service;

import br.com.techthordev.springboot.todos.request.PasswordUpdateRequest;
import br.com.techthordev.springboot.todos.response.UserResponse;
public interface UserService {

	UserResponse getUserInfo();
	void deleteUser();
	void updatePassword(PasswordUpdateRequest passwordUpdateRequest);
}