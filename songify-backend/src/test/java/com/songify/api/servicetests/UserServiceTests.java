package com.songify.api.servicetests;

import com.songify.api.model.Role;
import com.songify.api.model.User;
import com.songify.api.model.dto.LoginRequest;
import com.songify.api.model.dto.UserDto;
import com.songify.api.service.LoginService;
import com.songify.api.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @Test
    void getAllUsersTest(){
        userService.addUser(new UserDto("number1", "first", "uno", new Role()));
        userService.addUser(new UserDto("number2", "second", "dos", new Role()));
        userService.addUser(new UserDto("number3", "third", "tres", new Role()));

        List<User> users = userService.getUsers();

        assert users != null;
        Assertions.assertEquals("number2",users.get(1).getUsername());
    }

    @Test
    void getUserByIdIsPresentTest(){
        HttpStatus status = userService.getUserById(500L).getStatusCode();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, status);
    }

    @Test
    void getUserByUsernameAndPasswordTest(){
        User user = userService.getUserByUsernameAndPassword("number1", "first");

        Assertions.assertEquals("uno", user.getEmail());
    }

    @Test
    void getUserByUsernameAndPasswordWrongInputTest(){
        User user = userService.getUserByUsernameAndPassword("fghak", "AwghfH");

        Assertions.assertNull(user);
    }

    @Test
    void updateUserTest(){
        userService.updateUser(3L, new UserDto("updatedUsername", "updatedPassword", "updatedEmail", new Role())).getBody();

        Assertions.assertEquals("updatedUsername", userService.getUserById(3L).getBody().getUsername());
    }

    @Test
    void deleteUserTest(){
        userService.deleteUser(3L);

        List<User> users = userService.getUsers();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            users.get(3);
        });
    }

    @Test
    void tryLoginTest(){
        Role role = new Role();
        role.setId(1L);
        role.setName("role");
        User added = userService.addUser(new UserDto("username", "password", "email@gmail.com", role)).getBody();

        User user = loginService.tryLogin(new LoginRequest("username", "password"));

        assert added != null;
        Assertions.assertEquals(added.getUsername(), user.getUsername());
    }
}
