package com.songify.api.service.impl;

import com.songify.api.model.User;
import com.songify.api.model.dto.LoginRequest;
import com.songify.api.service.AuthService;
import com.songify.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public User login(LoginRequest request) {
        return this.userService.getUserByUsernameAndPassword(request.getUsername(), request.getPassword());
    }
}
