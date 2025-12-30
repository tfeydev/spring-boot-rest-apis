package br.com.techthordev.springboot.todos.util;

import br.com.techthordev.springboot.todos.entity.User;

public interface FindAuthenticatedUser {

    User getAuthenticatedUser();
}