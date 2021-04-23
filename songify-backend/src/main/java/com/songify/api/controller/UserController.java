package com.songify.api.controller;

import com.songify.api.service.UserService;
import com.songify.api.model.User;
import com.songify.api.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/users")
public class UserController {

    @Autowired
    private UserService userManager;

    @GetMapping("")
    public List<User> getUsers(){return userManager.getUsers();}

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return this.userManager.getUserById(id);
    }

    @PostMapping("")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDTO){ return this.userManager.addUser(userDTO); }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @RequestBody UserDto userDTO) { return this.userManager.updateUser(userId, userDTO); }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long userId){ return this.userManager.deleteUser(userId); }
}