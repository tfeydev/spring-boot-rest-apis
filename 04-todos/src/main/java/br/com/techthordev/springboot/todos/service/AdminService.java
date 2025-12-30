package br.com.techthordev.springboot.todos.service;

import java.util.List;

import br.com.techthordev.springboot.todos.response.UserResponse;

public interface AdminService {

    List<UserResponse> getAllUsers();
    UserResponse promoteToAdmin(long userId);
    void deleteNonAdminUser(long userId);
    
}