package com.docker.controller;

import com.docker.entities.User;
import com.docker.service.IUserService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user)
    {
        String name = userService.saveUser(user);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<User> getUser(@RequestParam String name){
        User user = userService.getUser(name);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
