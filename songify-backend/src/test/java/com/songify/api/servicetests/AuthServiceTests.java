package com.songify.api.servicetests;

import com.songify.api.model.Role;
import com.songify.api.model.User;
import com.songify.api.model.dto.LoginRequest;
import com.songify.api.model.dto.UserDto;
import com.songify.api.service.AuthService;
import com.songify.api.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class AuthServiceTests {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Test
    void loginTest(){
        userService.addUser(new UserDto("logintestusername", "logintestpassword", "email", new Role()));
        User user = authService.login(new LoginRequest("logintestusername", "logintestpassword"));

        Assertions.assertEquals( "logintestusername", user.getUsername());
    }
}
