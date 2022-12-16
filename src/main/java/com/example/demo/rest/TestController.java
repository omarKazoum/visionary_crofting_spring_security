package com.example.demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.persistence.Access;

@RestController
public class TestController {
    @RequestMapping("/user")
    @RolesAllowed("USER")
    String userHome(){
        return "hello, user";
    }
    @RequestMapping("/admin")
    @RolesAllowed("ADMIN")
    String adminHome(){
        return "hello, admin";
    }
    @RequestMapping("/test")
    @RolesAllowed({"USER"})
    String test(){
        return "hello, test";
    }
}
