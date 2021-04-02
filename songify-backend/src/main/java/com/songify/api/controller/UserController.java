package com.songify.api.controller;

import com.songify.api.exceptions.UserNotFoundException;
import com.songify.api.manager.UserManager;
import com.songify.api.model.User;
import com.songify.api.repository.RoleRepository;
import com.songify.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserManager userManager;

    @GetMapping("users")
    public List<User> getUsers(){return userManager.getUsers();}

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return this.userManager.getUserById(id);
    }

    @PostMapping("users/add")
    public User newUser(@RequestBody User newUser){
        newUser.setRole(roleRepository.findByName(newUser.getRole().getName()));
        return this.userRepository.save(newUser);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @RequestBody User userDetails)
    {
        //find user
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        //update the user details
        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setPasshash(userDetails.getPasshash());
        //no change role for now

        //save new user details and return updated user and OK http
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id") Long userId){
        //find user with given id
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        //delete user
        userRepository.delete(user);

        //return successful deletion
        return ResponseEntity.ok("Success");
    }
}
