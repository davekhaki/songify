package com.songify.api.controller;

import com.songify.api.model.dto.RoleDto;
import com.songify.api.model.Role;
import com.songify.api.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("")
    public List<Role> getRoles(){ return this.roleService.getRoles(); }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id){ return this.roleService.getRoleById(id); }

    @PostMapping("")
    public Role addRole(@RequestBody RoleDto dto){ return this.roleService.addRole(dto); }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody RoleDto dto){ return this.roleService.updateRole(id , dto); }

    //no delete
}
