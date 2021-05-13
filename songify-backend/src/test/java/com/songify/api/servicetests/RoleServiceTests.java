package com.songify.api.servicetests;

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

    @Test
    void getAllRolesTest(){
        roleService.addRole(new RoleDto(1L, "test"));
        roleService.addRole(new RoleDto(2L, "testRole"));

        List<Role> roles = roleService.getRoles().getBody();

        Assertions.assertEquals(roles.get(0).getName(), "test");
        Assertions.assertEquals(roles.get(1).getName(), "testRole");
    }

    @Test
    void getRoleByIdTest(){
        Role role = new Role();
        role.setId(1L);
        role.setName("test");
        roleService.addRole(new RoleDto(1L, "test"));

        String roleName = roleService.getRoleById(1L).getBody().getName();

        Assertions.assertEquals(roleName, role.getName());
    }

    @Test
    void updateRoleTest(){
        RoleDto updatedRole = new RoleDto(1L, "Name");

        roleService.addRole(new RoleDto(1L, "test"));

        roleService.updateRole(1L, updatedRole);

        String newName = roleService.getRoleById(1L).getBody().getName();

        Assertions.assertEquals(updatedRole.getName(), newName);
    }
}
