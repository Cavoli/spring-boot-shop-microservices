package com.cavoli.auth.controller;

import com.cavoli.auth.model.User;
import com.cavoli.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable("name") String name){
        User user = this.userService.findUser(name);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
        User user = this.userService.findUser(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/{surname}")
    public ResponseEntity<User> getUserBySurname(@PathVariable("surname") String surname){
        User user = this.userService.findUser(surname);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        this.userService.saveUser(user);
        return new ResponseEntity<User>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> createUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
