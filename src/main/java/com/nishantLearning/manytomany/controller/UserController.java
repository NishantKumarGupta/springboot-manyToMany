package com.nishantLearning.manytomany.controller;

import com.nishantLearning.manytomany.DTO.UserDTO;
import com.nishantLearning.manytomany.model.User;
import com.nishantLearning.manytomany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<UserDTO> getAllUserController(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public void createUserController(@RequestBody User newUser){
        userService.createUser(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserController(@PathVariable Long id, @RequestBody User newUser){
        return userService.updateUser(id, newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserController(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
