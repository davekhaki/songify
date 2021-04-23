package com.songify.api.controller;

import com.songify.api.service.LoginService;
import com.songify.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/loginpoint")
public class LoginController {

    @Autowired
    private LoginService loginManager;

    @PostMapping("") //changed to post
    public ResponseEntity<User> tryLogin(@RequestParam String username, @RequestParam String passHash){
        return loginManager.tryLogin(username, passHash);
    }
}
