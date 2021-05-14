package com.songify.api.servicetests;

import com.songify.api.model.Role;
import com.songify.api.model.User;
import com.songify.api.model.dto.UserDto;
import com.songify.api.service.LoginService;
import com.songify.api.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class LoginServiceTests {

    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;

    @Test
    void tryLoginTest(){
        Role role = new Role();
        role.setId(1L);
        role.setName("role");
        User added = userService.addUser(new UserDto("username", "password", "email@gmail.com", role)).getBody();

        HttpStatus tryLoginHttpStatus = loginService.tryLogin("username", "password").getStatusCode();

        var users = userService.getUsers();

        User user = userService.getUserByUsernameAndPassword("username", "password");
        User user1 = userService.getUserById(0L).getBody();
        User user2 = userService.getUserById(1L).getBody();


        //Assertions.assertEquals(HttpStatus.OK, tryLoginHttpStatus);
        Assertions.assertEquals(added, user);
    }
}
