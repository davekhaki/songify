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
}
