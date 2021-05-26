package com.songify.api.service;

import com.songify.api.model.User;
import com.songify.api.model.dto.LoginRequest;

public interface AuthService {
    User login(LoginRequest request);
}
