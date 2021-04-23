package com.songify.api.controller;

import com.songify.api.model.dto.RoleDto;
import com.songify.api.service.RoleService;
import com.songify.api.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleManager;

    @GetMapping("")
    public ResponseEntity<List<Role>> getRoles(){ return this.roleManager.getRoles(); }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id){ return this.roleManager.getRoleById(id); }

    @PostMapping("")
    public ResponseEntity<Role> addRole(@RequestBody RoleDto Dto){ return this.roleManager.addRole(Dto); }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody RoleDto Dto){ return this.roleManager.updateRole(id , Dto); }

    //no delete
}
