package com.songify.api.service.impl;

import com.songify.api.model.dto.RoleDto;
import com.songify.api.exceptions.ResourceNotFoundException;
import com.songify.api.model.Role;
import com.songify.api.repository.RoleRepository;
import com.songify.api.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoles(){ return this.roleRepository.findAll(); }

    @Override
    public Role getRoleById(Long id){
        return roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("getRoleById, role not found"));
    }

    @Override
    public Role getRoleByName(String name){ return this.roleRepository.findByName(name); }

    @Override
    public Role addRole(RoleDto dto){
        Role role = new Role();
        role.setName(dto.getName());
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, RoleDto dto){
        var role = roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("update role, find by id"));
        role.setName(dto.getName());
        return roleRepository.save(role);
    }
}
