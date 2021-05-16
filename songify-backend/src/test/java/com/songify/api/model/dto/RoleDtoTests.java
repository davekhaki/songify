package com.songify.api.model.dto;

import com.songify.api.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleDtoTests {

    @Test
    void getIdTest(){
        RoleDto dto = new RoleDto(1L, "name");
        Assertions.assertEquals(1L, dto.getId());
    }

    @Test
    void setIdTest(){
        RoleDto dto = new RoleDto(1L, "name");
        dto.setId(2L);
        Assertions.assertEquals(2L, dto.getId());
    }

    @Test
    void getNameTest(){
        RoleDto dto = new RoleDto(1L, "name");
        Assertions.assertEquals("name", dto.getName());
    }

    @Test
    void setNameTest(){
        RoleDto dto = new RoleDto(1L, "name");
        dto.setName("new");
        Assertions.assertEquals("new", dto.getName());
    }
}
