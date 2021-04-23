package com.songify.api.controller;

import com.songify.api.model.dto.RoleDto;
import com.songify.api.service.RoleService;
import com.songify.api.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleManager;

    @GetMapping("")
    public ResponseEntity<List<Role>> getRoles(){ return this.roleManager.getRoles(); }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id){ return this.roleManager.getRoleById(id); }

    @PostMapping("")
    public ResponseEntity<Role> addRole(@RequestBody RoleDto dto){ return this.roleManager.addRole(dto); }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody RoleDto dto){ return this.roleManager.updateRole(id , dto); }

    //no delete
}
