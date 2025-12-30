package br.com.techthordev.springboot.todos.entity;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Embeddable;

@Embeddable
public class Authority implements GrantedAuthority {

    private String authority;
    
    public Authority() {}

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
