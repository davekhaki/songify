package com.songify.api.servicetests;

import com.songify.api.model.Role;
import com.songify.api.model.dto.RoleDto;
import com.songify.api.model.dto.UserDto;
import com.songify.api.service.RoleService;
import com.songify.api.service.UserService;
import com.songify.api.service.impl.AuthenticationUserDetailServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class AuthenticationUserDetailsServiceTests {

    @Autowired
    private AuthenticationUserDetailServiceImpl authUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Test
    void loadUserTest(){
        roleService.addRole(new RoleDto(35L, "test"));
        userService.addUser(new UserDto("name", "password", "mail", new Role("test")));
        var user = authUserDetailsService.loadUserByUsername("name");

        Assertions.assertNotNull(user);
    }

    @Test
    void loadUserIsNullTest(){
        var user = authUserDetailsService.loadUserByUsername("kkkkkkkkkkkkkKK");
        Assertions.assertNull(user);
    }
}
