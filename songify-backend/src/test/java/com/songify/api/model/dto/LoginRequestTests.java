package com.songify.api.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginRequestTests {

    @Test
    void getUsernameTest(){
        LoginRequest request = new LoginRequest("a username", "a password");
        Assertions.assertEquals("a username", request.getUsername());
    }

    @Test
    void setUsernameTest(){
        LoginRequest request = new LoginRequest("a username", "a password");
        request.setUsername("ok");
        Assertions.assertEquals("ok", request.getUsername());
    }

    @Test
    void getPasswordTest(){
        LoginRequest request = new LoginRequest("a username", "a password");
        Assertions.assertEquals("a password", request.getPassword());
    }

    @Test
    void setPasswordTest(){
        LoginRequest request = new LoginRequest("a username", "a password");
        request.setPassword("yep");
        Assertions.assertEquals("yep", request.getPassword());
    }
}
