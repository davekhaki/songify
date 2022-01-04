package com.songify.api.controller;

import com.songify.api.service.UserService;
import com.songify.api.model.User;
import com.songify.api.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

  //  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("")
    public List<User> getUsers(){ return userService.getAllUsers(); }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return this.userService.getUserById(id);
    }

    @PostMapping("")
    public User addUser(@RequestBody UserDto userDTO){ return this.userService.addUser(userDTO); }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId, @RequestBody UserDto userDTO) { return this.userService.updateUser(userId, userDTO); }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(value = "id") Long userId){ return this.userService.deleteUser(userId); }

    @GetMapping("/username/{id}")
    public String getUsername(@PathVariable Long id){return this.userService.getUsernameById(id); }
}