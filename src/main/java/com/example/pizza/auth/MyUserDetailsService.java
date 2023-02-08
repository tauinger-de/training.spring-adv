package com.example.pizza.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class MyUserDetailsService implements UserDetailsService {

    // generated with https://bcrypt-generator.com/ (12 rounds) from password "pwd"
    public static final String DEFAULT_PASSWORD = "$2a$12$TNAt7offtGR2STrVOhXngOwo4xv/1TiYbQ2CPCeDWL4B8.qGOZ0py";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var pwd = (username.equals("bad")) ? "xxxxxx" : DEFAULT_PASSWORD;
        return new User(
                username,
                pwd,
                List.of(
                        new SimpleGrantedAuthority("gast"),
                        new SimpleGrantedAuthority("ROLE_USER")
                )
        );
    }

}
