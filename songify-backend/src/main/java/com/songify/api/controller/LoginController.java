package com.songify.api.controller;

import com.songify.api.manager.LoginManager;
import com.songify.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("v1/api/login")
public class LoginController {

    @Autowired
    private LoginManager loginManager;

    @PostMapping("") //change to post
    public ResponseEntity<User> tryLogin(@RequestParam String username, @RequestParam String pass_hash){
        return loginManager.tryLogin(username, pass_hash);
    }
}
