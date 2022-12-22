package com.example.demo.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserDetailsResource {
    @Autowired
    AuthenticationManager authenticationManager;
    @GetMapping("/info")
    ResponseEntity<String> getUserInfo(){
        return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication().getName(), HttpStatus.OK);
    }
}
