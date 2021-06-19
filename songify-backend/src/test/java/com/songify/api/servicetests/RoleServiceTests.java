package com.songify.api.servicetests;

import com.songify.api.exceptions.ResourceNotFoundException;
import com.songify.api.model.Role;
import com.songify.api.model.dto.RoleDto;
import com.songify.api.repository.RoleRepository;
import com.songify.api.service.RoleService;
import com.songify.api.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class RoleServiceTests {

    RoleService roleService;
    RoleRepository roleRepository;
    List<Role> roles;

    @BeforeEach
    void createTestData(){
        this.roleRepository = Mockito.mock(RoleRepository.class);
        this.roleService = new RoleServiceImpl(roleRepository);

        this.roles = new ArrayList<>();
        roles.add(new Role("First"));
        roles.add(new Role("Second"));
        roles.add(new Role("Third"));
        roles.add(new Role("Fourth"));
    }

    @Test
    void getAllRolesTest(){
        Mockito.when(roleRepository.findAll()).thenReturn(roles);
        List<Role> actualRoles = roleService.getRoles();

        Assertions.assertEquals(actualRoles, roles);
    }

    @Test
    void getRoleByIdTest(){
        Mockito.when(roleRepository.findById(2L)).thenReturn(Optional.of(roles.get(2)));
        String roleName = roleService.getRoleById(2L).getName();

        Assertions.assertEquals("Third", roleName);
    }

    @Test
    void addRoleTest(){
        Role newRole = new Role("Fourth");
        newRole.setId(10L);
        Mockito.when(roleRepository.save(newRole)).thenReturn(roles.get(3));
        Role role = roleService.addRole(new RoleDto(10L, "Fourth"));

        Assertions.assertEquals("Fourth", newRole.getName());
    }

    @Test
    void updateRoleTest(){
        Mockito.when(roleRepository.findById(1L)).thenReturn(Optional.of(roles.get(1)));
        Mockito.when(roleRepository.save(new Role("new name"))).thenReturn(new Role("new name"));

        roleService.updateRole(1L, new RoleDto(1L, "new name"));

        String newName = roleService.getRoleById(1L).getName();

        Assertions.assertEquals("new name", newName);
    }

    @Test
    void updateRoleResourceNotFoundExceptionThrownTest(){
        Mockito.when(roleRepository.findById(500L)).thenThrow(new ResourceNotFoundException("yep"));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            roleService.updateRole(500L, new RoleDto(5325L, "exception!!"));
        });
    }
}
