package com.songify.api.controller;

import com.songify.api.model.dto.LoginRequest;
import com.songify.api.service.AuthService;
import com.songify.api.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("") //changed to post
    public User login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
}
