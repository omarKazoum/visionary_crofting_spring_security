package com.example.demo.rest.controllers;

import com.example.demo.exceptions.RegisterException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserDetailsService;
import com.example.demo.service.dto.UserLoginDTO;
import com.example.demo.service.dto.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;
    @PostMapping("/register")
    ResponseEntity register(@RequestBody UserRegisterDTO userDTO){
        try{
            UserRegisterDTO userRegisterDTO=userDetailsService.register(userDTO);
            return new ResponseEntity<>(userRegisterDTO, HttpStatus.ACCEPTED);

        }catch (RegisterException exception){
            exception.printStackTrace();
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    ResponseEntity login(@RequestBody UserLoginDTO loginDTO) {
        try {
            return new ResponseEntity<>(userDetailsService.login(loginDTO), HttpStatus.ACCEPTED);
        }catch (Exception exception){
            return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);
        }
    }


}
