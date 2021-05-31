package com.songify.api.servicetests;

import com.songify.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class AuthServiceTests {

    @Autowired
    private AuthService authService;
}
