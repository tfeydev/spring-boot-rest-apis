package br.com.techthordev.springboot.todos.service;

import br.com.techthordev.springboot.todos.request.AuthenticationRequest;
import br.com.techthordev.springboot.todos.request.RegisterRequest;
import br.com.techthordev.springboot.todos.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest input) throws Exception;
    AuthenticationResponse login(AuthenticationRequest request);
}
