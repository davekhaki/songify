package com.songify.api.controller;

import com.songify.api.dto.RoleDTO;
import com.songify.api.manager.RoleManager;
import com.songify.api.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/roles")
public class RoleController {

    @Autowired
    private RoleManager roleManager;

    @GetMapping("")
    public ResponseEntity<List<Role>> getRoles(){ return this.roleManager.getRoles(); }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id){ return this.roleManager.getRoleById(id); }

    @PostMapping("")
    public ResponseEntity<Role> addRole(@RequestBody RoleDTO Dto){ return this.roleManager.addRole(Dto); }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody RoleDTO Dto){ return this.roleManager.updateRole(id , Dto); }

    //no delete
}
