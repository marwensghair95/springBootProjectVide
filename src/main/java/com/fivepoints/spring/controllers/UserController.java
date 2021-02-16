package com.fivepoints.spring.controllers;

import com.fivepoints.spring.entities.User;
import com.fivepoints.spring.payload.responses.MessageResponse;
import com.fivepoints.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> saveNewUser(@RequestBody User user)
    {
        User savedUser =  this.userService.saveNewUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> listUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserByID(@PathVariable("id") long id)
    {
        User user = this.userService.findUserByID(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateUserByID(@PathVariable("id") long id, @RequestBody User user)
    {
        String message = this.userService.updateUserByID(id, user);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteUserById(@PathVariable("id") long id)
    {
        String message = this.userService.deleteUserById(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

}
