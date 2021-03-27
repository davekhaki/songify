package com.songify.api.controller;

import com.songify.api.exceptions.UserNotFoundException;
import com.songify.api.model.User;
import com.songify.api.repository.RoleRepository;
import com.songify.api.repository.UserRepository;
import org.hibernate.annotations.SQLDeleteAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("v1/api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("users")
    public List<User> getUsers(){return this.userRepository.findAll();}

    @GetMapping("users/{id}")
    public User getUserById(@PathVariable Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("users/add")
    public User newUser(@RequestBody User newUser){
        newUser.setRole(roleRepository.findByName(newUser.getRole().getName()));
        return this.userRepository.save(newUser);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @RequestBody User userDetails)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setPasshash(userDetails.getPasshash());
        //no change role for now

        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id") Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.delete(user);

        return ResponseEntity.ok("Success");
    }


    @GetMapping("login") //change to post
    public User tryLogin(@RequestParam String username, @RequestParam String pass_hash){
        return this.userRepository.findByUsernameAndPasshash(username, pass_hash);
    }
}
