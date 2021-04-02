package com.songify.api;

import com.songify.api.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApiApplicationTests {

    @Test
    void contextLoads() {
        User user = new User();
        user.setEmail("email yes");

        assertEquals("email yes", user.getEmail());
    }

}
