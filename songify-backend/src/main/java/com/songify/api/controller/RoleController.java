package com.songify.api.controller;

import com.songify.api.model.Role;
import com.songify.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("roles")
    public List<Role> getRoles(){
        return this.roleRepository.findAll();
    }
}