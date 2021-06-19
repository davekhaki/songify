package com.songify.api.servicetests;

import com.songify.api.model.Role;
import com.songify.api.model.User;
import com.songify.api.model.dto.UserDto;
import com.songify.api.repository.RoleRepository;
import com.songify.api.repository.UserRepository;
import com.songify.api.service.RoleService;
import com.songify.api.service.UserService;
import com.songify.api.service.impl.RoleServiceImpl;
import com.songify.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserServiceTests {

    UserService userService;
    UserRepository userRepository;
    RoleRepository roleRepository;
    RoleService roleService;
    List<User> users;

    @BeforeEach
    void createTestData(){
        this.userRepository = Mockito.mock(UserRepository.class);
        this.roleRepository = Mockito.mock(RoleRepository.class);
        this.roleService = new RoleServiceImpl(roleRepository);
        this.userService = new UserServiceImpl(userRepository, roleService);

        this.users = new ArrayList<>();
        users.add(new User("username", "FIRST", "email", new Role()));
        users.add(new User("username", "gaming", "password", new Role()));
        users.add(new User("username", "123456789", "extra!", new Role()));
        users.add(new User("username", "password", "email", new Role()));
    }

    @Test
    void getAllUsersTest(){
        Mockito.when(userRepository.findAll()).thenReturn(users);
        List<User> allUsers = userService.getAllUsers();

        Assertions.assertIterableEquals(users, allUsers);
    }

    @Test
    void getUserById(){
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(users.get(2)));
        User user = userService.getUserById(2L);

        Assertions.assertEquals("extra!", user.getPassword());
    }

    @Test
    void getUserByUsernameTest(){
        Mockito.when(userRepository.findByUsername("123456789")).thenReturn(users.get(2));
        User user = userService.getUserByUsername("123456789");

        Assertions.assertEquals("extra!", user.getPassword());
    }

    @Test
    void getUserByUsernameAndPasswordTest(){
        Mockito.when(userRepository.findByUsername("gaming")).thenReturn(new User("username", "gaming", "$2a$10$p4lyJPB7eVBlEjj3a9Yt4.QQm2iFlts9T3w6cMg3GYWXn/vAwgo8m", new Role()));

        User user = userService.getUserByUsernameAndPassword("gaming", "user1");

        Assertions.assertNotNull(user);
    }

    @Test
    void getUserByUsernameAndPasswordWrongInputTest(){
        Mockito.when(userRepository.findByUsername("newuser")).thenReturn(new User("email", "newuser", "$2a$10$p4lyJPB7eVBlEjj3a9Yt4.QQm2iFlts9T3w6cMg3GYWXn/vAwgo8m", new Role()));
        User user = userService.getUserByUsernameAndPassword("newuser", "yessir");

        Assertions.assertNull(user);
    }

    @Test
    void getUserByUsernameAndPasswordBothWrongInputTest(){
        Mockito.when(userRepository.findByUsername("newuser")).thenReturn(null);
        User user = userService.getUserByUsernameAndPassword("newuser", "yessir");

        Assertions.assertNull(user);
    }

    @Test
    void addUserTest(){
        User newUser = new User("email.", "username.", "password.", new Role("role."));
        Mockito.when(userRepository.save(newUser)).thenReturn(newUser);

        User actual = userService.addUser(new UserDto("username.", "password.", "email.", new Role("role.")));

        Assertions.assertEquals(newUser.getUsername(), actual.getUsername());
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

    @Test
    void deleteUserTest(){
        Mockito.when(userRepository.findById(5L)).thenReturn(Optional.ofNullable(users.get(3)));
        users.remove(3);
        userService.deleteUser(5L);

        Assertions.assertEquals(3, users.size());
    }

    @Test
    void saveUserTest(){
        User newUser = new User("email", "username", "password", new Role());
        Mockito.when(userRepository.save(newUser)).thenReturn(newUser);

        User actual = userService.save(newUser);

        Assertions.assertEquals("username", actual.getUsername());
    }

    @Test
    void getUsernameByIdTest(){
        Mockito.when(userRepository.findById(3L)).thenReturn(Optional.ofNullable(users.get(2)));

        String actual = userService.getUsernameById(3L);

        Assertions.assertEquals("123456789", actual);
    }

}
