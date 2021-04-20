package com.songify.api.manager;

import com.songify.api.model.dto.RoleDTO;
import com.songify.api.exceptions.ResourceNotFoundException;
import com.songify.api.model.Role;
import com.songify.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleManager {

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<List<Role>> getRoles(){
        var roles = this.roleRepository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    public ResponseEntity<Role> getRoleById(Long id){
        var role = roleRepository.findById(id);

        if(role.isPresent()){
            return new ResponseEntity<>(role.get(), HttpStatus.OK);
        }
        else return null;
    }

    public ResponseEntity<Role> addRole(RoleDTO Dto){
        Role role = new Role();
        role.setName(Dto.getName());
        return new ResponseEntity<>(roleRepository.save(role), HttpStatus.OK);
    }

    public ResponseEntity<Role> updateRole(Long id, RoleDTO Dto){
        var old = roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("update role, find by id"));
        old.setName(Dto.getName());
        final Role updated = roleRepository.save(old);
        return ResponseEntity.ok(updated);
    }
}
