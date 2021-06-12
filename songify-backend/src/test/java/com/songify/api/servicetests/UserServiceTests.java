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
import java.util.Optional;

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
        users.add(new User("username", "gaming", "password", new Role()));
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

    @Test
    void getUserByUsernameAndPasswordTest(){
        Mockito.when(userRepository.findByUsername("gaming")).thenReturn(new User("username", "gaming", "$2a$10$p4lyJPB7eVBlEjj3a9Yt4.QQm2iFlts9T3w6cMg3GYWXn/vAwgo8m", new Role()));
        //
        User user = userService.getUserByUsernameAndPassword("gaming", "user1");

        Assertions.assertNotNull(user);
    }

    @Test
    void getUserByUsernameAndPasswordWrongInputTest(){
        Mockito.when(userRepository.findByUsername("")).thenReturn(new User("facts","facts", "facts", new Role()));
        User user = userService.getUserByUsernameAndPassword("newuser", "yessir");

        Assertions.assertNull(user);
    }

    @Test
    void updateUserTest(){
        User user = new User("email", "username", "password", new Role());
        user.setId(5L);
        Mockito.when(userRepository.findById(5L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(new User("updatedUsername", ".", ".", new Role()));
        userService.updateUser(5L, new UserDto("updatedUsername", "updatedPassword", "updatedEmail", new Role()));

        Assertions.assertEquals("updatedUsername", user.getUsername());
    }

//    @Test()
//    void updateUserThrowsUserNotFoundExceptionTest(){
//        User toBeSaved = new User("email", "username", "password", new Role());
//        toBeSaved.setId(500L);
//        Mockito.when(userRepository.findById(toBeSaved.getId())).thenThrow(UserNotFoundException.class);
//        Assertions.assertThrows(UserNotFoundException.class, () -> {
//            userService.updateUser(578123L, new UserDto(".", ".", ".", new Role()));
//        });
//    }
}
