package com.songify.api.service;

import com.songify.api.model.Role;
import com.songify.api.model.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();

    Role getRoleById(Long id);

    Role getRoleByName(String name);

    Role addRole(RoleDto dto);

    Role updateRole(Long id, RoleDto dto);
}
