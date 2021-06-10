package com.songify.api.servicetests;

import com.songify.api.exceptions.UserNotFoundException;
import com.songify.api.model.Role;
import com.songify.api.model.User;
import com.songify.api.model.dto.UserDto;
import com.songify.api.repository.UserRepository;
import com.songify.api.service.UserService;
import com.songify.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceTests {

    UserService userService;
    UserRepository userRepository;
    List<User> users;

    @BeforeEach
    void createTestData(){
        this.userRepository = Mockito.mock(UserRepository.class);
        this.userService = new UserServiceImpl(userRepository);

        this.users = new ArrayList<>();
        users.add(new User("username", "password", "email", new Role()));
        users.add(new User("username", "password", "email", new Role()));
        users.add(new User("username", "password", "email", new Role()));
        users.add(new User("username", "password", "email", new Role()));


    }

    @Test
    void getAllUsersTest(){
        Mockito.when(userRepository.findAll()).thenReturn(users);
        List<User> users = userService.getAllUsers();

        assert users != null;
        Assertions.assertEquals(4, users.size());
    }

//    @Test
//    void getUserByUsernameAndPasswordTest(){
//        userService.addUser(new UserDto("n", "p", "e", new Role()));
//        User user = userService.getUserByUsernameAndPassword("n", "p");
//        userService.deleteUser(userService.getUserByUsername("n").getId());
//        Assertions.assertNotNull(user);
//    }
//
//    @Test
//    void getUserByUsernameAndPasswordWrongInputTest(){
//        User user = userService.getUserByUsernameAndPassword("number1", "AwghfH");
//
//        Assertions.assertNull(user);
//    }
//
//    @Test
//    void updateUserTest(){
//        userService.updateUser(3L, new UserDto("updatedUsername", "updatedPassword", "updatedEmail", new Role()));
//
//        Assertions.assertEquals("updatedUsername", userService.getUserById(3L).getUsername());
//    }
//
//    @Test
//    void updateUserThrowsUserNotFoundExceptionTest(){
//        Assertions.assertThrows(UserNotFoundException.class, () -> {
//            userService.updateUser(578123L, new UserDto(".", ".", ".", new Role()));
//        });
//    }
//
//    @Test
//    void deleteUserTest(){
//        userService.deleteUser(3L);
//
//        List<User> users = userService.getAllUsers();
//
//        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
//            users.get(4);
//        });
//    }
}
