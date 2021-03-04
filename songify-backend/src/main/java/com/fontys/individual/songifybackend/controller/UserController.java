package com.fontys.individual.songifybackend.controller;

import com.fontys.individual.songifybackend.exceptions.UserNotFoundException;
import com.fontys.individual.songifybackend.model.User;
import com.fontys.individual.songifybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    @GetMapping("users/{id}")
    public User getUserById(@PathVariable Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("users")
    public User newUser(@RequestBody User newUser){
        return this.userRepository.save(newUser);
    }






    @GetMapping("login")
    public User tryLogin(@RequestParam String username, @RequestParam String pass_hash){
        return this.userRepository.findByUsernameAndPasshash(username, pass_hash);
    }
}
