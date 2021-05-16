package com.songify.api.servicetests;

import com.songify.api.model.Role;
import com.songify.api.model.User;
import com.songify.api.model.dto.UserDto;
import com.songify.api.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

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
    void getUserByUsernameAndPassword(){
        User user = userService.getUserByUsernameAndPassword("number1", "first");

        Assertions.assertEquals("uno", user.getEmail());
    }

    @Test
    void updateUser(){
        userService.updateUser(3L, new UserDto("updatedUsername", "updatedPassword", "updatedEmail", new Role())).getBody();

        Assertions.assertEquals("updatedUsername", userService.getUserById(3L).getBody().getUsername());
    }

    @Test
    void deleteUser(){
        userService.deleteUser(2L);

        List<User> users = userService.getUsers();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            users.get(2);
        });
    }
}
