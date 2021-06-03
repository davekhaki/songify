package com.songify.api.servicetests;

import com.songify.api.exceptions.UserNotFoundException;
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
class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    void getAllUsersTest(){
        userService.addUser(new UserDto("number1", "first", "uno", new Role()));
        userService.addUser(new UserDto("number2", "second", "dos", new Role()));
        userService.addUser(new UserDto("number3", "third", "tres", new Role()));

        List<User> users = userService.getAllUsers();

        assert users != null;
        Assertions.assertNotNull(users);
    }

    @Test
    void getUserByUsernameAndPasswordTest(){
        userService.addUser(new UserDto("n", "p", "e", new Role()));
        User user = userService.getUserByUsernameAndPassword("n", "p");
        userService.deleteUser(userService.getUserByUsername("n").getId());
        Assertions.assertNotNull(user);
    }

    @Test
    void getUserByUsernameAndPasswordWrongInputTest(){
        User user = userService.getUserByUsernameAndPassword("number1", "AwghfH");

        Assertions.assertNull(user);
    }

    @Test
    void updateUserTest(){
        userService.updateUser(3L, new UserDto("updatedUsername", "updatedPassword", "updatedEmail", new Role()));

        Assertions.assertEquals("updatedUsername", userService.getUserById(3L).getUsername());
    }

    @Test
    void updateUserThrowsUserNotFoundExceptionTest(){
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(578123L, new UserDto(".", ".", ".", new Role()));
        });
    }

    @Test
    void deleteUserTest(){
        userService.deleteUser(3L);

        List<User> users = userService.getAllUsers();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            users.get(4);
        });
    }
}
