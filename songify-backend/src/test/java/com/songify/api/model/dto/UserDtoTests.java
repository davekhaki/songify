package com.songify.api.model.dto;

import com.songify.api.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDtoTests {

    @Test
    void getUsernameTest(){
        UserDto dto = new UserDto("username", "pw", "mail", new Role());
        Assertions.assertEquals("username", dto.getUsername());
    }

    @Test
    void setUsernameTest(){
        UserDto dto = new UserDto("username", "pw", "mail", new Role());
        dto.setUsername("new");
        Assertions.assertEquals("new", dto.getUsername());
    }

    @Test
    void getPasswordTest(){
        UserDto dto = new UserDto("username", "pw", "mail", new Role());
        Assertions.assertEquals("pw", dto.getPassword());
    }

    @Test
    void setPasswordTest(){
        UserDto dto = new UserDto("username", "pw", "mail", new Role());
        dto.setPassword("h");
        Assertions.assertEquals("h", dto.getPassword());
    }

    @Test
    void getEmailTest(){
        UserDto dto = new UserDto("username", "pw", "mail", new Role());
        Assertions.assertEquals("mail", dto.getEmail());
    }

    @Test
    void setEmailTest(){
        UserDto dto = new UserDto("username", "pw", "mail", new Role());
        dto.setEmail("gmail.com");
        Assertions.assertEquals("gmail.com", dto.getEmail());
    }

    @Test
    void getRoleTest(){
        Role role = new Role();
        UserDto dto = new UserDto("username", "pw", "mail", role);
        Assertions.assertEquals(role, dto.getRole());
    }

    @Test
    void setRoleTest(){
        Role role = new Role();
        role.setName("setting");
        UserDto dto = new UserDto("username", "pw", "mail", new Role());
        dto.setRole(role);
        Assertions.assertEquals(role, dto.getRole());
    }
}
