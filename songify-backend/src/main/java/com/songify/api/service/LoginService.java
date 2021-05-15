package com.songify.api.service;

import com.songify.api.model.User;
import com.songify.api.model.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    public User tryLogin(LoginRequest requestData){
        return this.userService.getUserByUsernameAndPassword(requestData.getUsername(), requestData.getPassword());
    }
}