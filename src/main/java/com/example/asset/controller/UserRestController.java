package com.example.asset.controller;

import com.example.asset.aop.LogExecutionTime;
import com.example.asset.model.User;
import com.example.asset.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserRestController {

    @Autowired
    UserService userService;

    @LogExecutionTime
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getOneUser(id);
    }

    @LogExecutionTime
    @PostMapping ("/user")
    public void insertUser(@RequestBody User user) {
         userService.insertUser(user);
    }

    @LogExecutionTime
    @GetMapping("/user/")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @LogExecutionTime
    @PutMapping("/user")
    public void updateUser(@RequestBody User user){

        userService.updateUser( user);
    }

    @LogExecutionTime
    @DeleteMapping ("/user/{id}")
    public void deleteUser(@PathVariable int  id){

        userService.deleteUser( id);

    }

    @LogExecutionTime
    @GetMapping
    public User getByPhone(@PathVariable int phone) {
        return userService.getByPhone(phone);
    }

}
