package com.songify.api.controller;

import com.songify.api.model.dto.LoginRequest;
import com.songify.api.service.LoginService;
import com.songify.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/login")
public class LoginController {

    @Autowired
    private LoginService loginManager;

    @PostMapping("") //changed to post
    public User tryLogin(@RequestBody LoginRequest loginRequestData){
        return loginManager.tryLogin(loginRequestData);
    }
}
