package com.songify.api.servicetests;

import com.songify.api.model.Role;
import com.songify.api.model.User;
import com.songify.api.model.dto.LoginRequest;
import com.songify.api.repository.UserRepository;
import com.songify.api.service.AuthService;
import com.songify.api.service.UserService;
import com.songify.api.service.impl.AuthServiceImpl;
import com.songify.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthServiceTests {


    private AuthService authService;
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void createTestData(){
        this.userRepository = Mockito.mock(UserRepository.class);
        this.userService = new UserServiceImpl(userRepository);
        this.authService = new AuthServiceImpl(userService);
    }

    @Test
    void loginTest(){
        Mockito.when(userRepository.findByUsername("logintest")).thenReturn(new User("email", "logintest", "$2y$10$.gJzgwLicWlhuOuoeWgikOHSIoQ.MQcOgDOssIU0ISzpAqhrGd.E.", new Role()));
        User user = authService.login(new LoginRequest("logintest", "loginpass"));

        Assertions.assertEquals( "email", user.getEmail());
    }
}
