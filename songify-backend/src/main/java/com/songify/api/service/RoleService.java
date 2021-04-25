package com.songify.api.service;

import com.songify.api.model.dto.RoleDto;
import com.songify.api.exceptions.ResourceNotFoundException;
import com.songify.api.model.Role;
import com.songify.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public ResponseEntity<List<Role>> getRoles(){
        var roles = this.roleRepository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    public ResponseEntity<Role> getRoleById(Long id){
        var role = roleRepository.findById(id);

        return role.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElse(null);
    }

    public ResponseEntity<Role> addRole(RoleDto dto){
        Role role = new Role();
        role.setName(dto.getName());
        return new ResponseEntity<>(roleRepository.save(role), HttpStatus.OK);
    }

    public ResponseEntity<Role> updateRole(Long id, RoleDto dto){
        var old = roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("update role, find by id"));
        old.setName(dto.getName());
        final Role updated = roleRepository.save(old);
        return ResponseEntity.ok(updated);
    }
}
