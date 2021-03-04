package com.fontys.individual.songifybackend.controller;

import com.fontys.individual.songifybackend.model.Role;
import com.fontys.individual.songifybackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("roles")
    public List<Role> getRoles(){
        return this.roleRepository.findAll();
    }
}
