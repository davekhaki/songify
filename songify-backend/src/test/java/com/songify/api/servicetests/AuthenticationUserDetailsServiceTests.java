package com.songify.api.servicetests;

import com.songify.api.model.Role;
import com.songify.api.model.User;
import com.songify.api.repository.UserRepository;
import com.songify.api.service.UserService;
import com.songify.api.service.impl.AuthenticationUserDetailServiceImpl;
import com.songify.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class AuthenticationUserDetailsServiceTests {

    private AuthenticationUserDetailServiceImpl authUserDetailsService;
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void createTestData(){
        this.userRepository = Mockito.mock(UserRepository.class);
        this.userService = new UserServiceImpl(userRepository);
        this.authUserDetailsService = new AuthenticationUserDetailServiceImpl(userService);
    }

    @Test
    void loadUserTest(){
        Mockito.when(userRepository.findByUsername("username")).thenReturn(new User("email", "username", "password", new Role("role")));

        UserDetails user = authUserDetailsService.loadUserByUsername("username");

        Assertions.assertEquals("password", user.getPassword());
    }

    @Test
    void loadUserFailTest(){
        Mockito.when(userRepository.findByUsername("fail test")).thenReturn(null);

        UserDetails user = authUserDetailsService.loadUserByUsername("fail test");

        Assertions.assertNull(user);
    }
}
