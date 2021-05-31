package com.songify.api.servicetests;

import com.songify.api.exceptions.ResourceNotFoundException;
import com.songify.api.model.Role;
import com.songify.api.model.dto.RoleDto;
import com.songify.api.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class RoleServiceTests {

    @Autowired
    RoleService roleService;

    void createTestData(){
        roleService.addRole(new RoleDto(1L, "test"));
        roleService.addRole(new RoleDto(2L, "test2"));
        roleService.addRole(new RoleDto(3L, "name"));
        roleService.addRole(new RoleDto(4L, "no"));
    }

    @Test
    void getAllRolesTest(){
        createTestData();
        List<Role> roles = roleService.getRoles();

        Assertions.assertEquals("test", roles.get(0).getName());
        Assertions.assertEquals("test2", roles.get(1).getName());
    }

    @Test
    void getRoleByIdTest(){
        Role role = new Role();
        role.setId(4L);
        role.setName("no");

        String roleName = roleService.getRoleById(4L).getName();

        Assertions.assertEquals("no", roleName);
    }

    @Test
    void updateRoleTest(){
        RoleDto updatedRole = new RoleDto(1L, "Name");

        roleService.updateRole(1L, updatedRole);

        String newName = roleService.getRoleById(1L).getName();

        Assertions.assertEquals(updatedRole.getName(), newName);
    }

    @Test
    void updateRoleResourceNotFoundExceptionThrownTest(){
        try{ // try catch = only one invocation possibly thrown
            roleService.updateRole(7573L, new RoleDto(58185L, "yep"));
            Assertions.fail("Expected a ResourceNotFoundException to be thrown");
        } catch (ResourceNotFoundException e){
            Assertions.assertEquals("Could not find resource: update role, find by id", e.getMessage());
        }
    }
}
