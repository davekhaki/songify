package com.songify.api.controller;

import com.songify.api.exceptions.UserNotFoundException;
import com.songify.api.model.User;
import com.songify.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "https://localhost:3000")
@RestController
@RequestMapping("v1/api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List<User> getUsers(){return this.userRepository.findAll();}

    @GetMapping("users/{id}")
    public User getUserById(@PathVariable Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("users/add")
    public User newUser(@RequestBody User newUser){ return this.userRepository.save(newUser);}

    @GetMapping("login")
    public User tryLogin(@RequestParam String username, @RequestParam String pass_hash){
        return this.userRepository.findByUsernameAndPasshash(username, pass_hash);
    }
}
