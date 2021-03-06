package com.songify.api.service.impl;

import com.songify.api.exceptions.UserNotFoundException;
import com.songify.api.model.User;
import com.songify.api.model.dto.UserDto;
import com.songify.api.repository.UserRepository;
import com.songify.api.service.RoleService;
import com.songify.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUsers(){ return this.userRepository.findAll(); }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        User user = getUserByUsername(username);
        if(user != null){
            if(passwordEncoder.matches(password, user.getPassword())){
                return user;
            }
            else return null;
        }
        else return null;
    }

    @Override
    public User addUser(UserDto dto) { // add automatic mapper ?
        User user = new User();

        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(roleService.getRoleByName(dto.getRole().getName()));

        this.userRepository.save(user); //save the user
        return user;
    }

    @Override
    public User updateUser(Long userId, UserDto dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)); //find user

        if(dto.getEmail() != null){
            user.setEmail(dto.getEmail()); //update the user details
        }
        if(dto.getUsername() != null){
            user.setUsername(dto.getUsername());
        }
        //no change password
        //no change role

        //save new user details and return updated user
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);

        return "Success";
    }

    @Override
    public User save(User user){
        return this.userRepository.save(user);
    }

    @Override
    public String getUsernameById(Long id) { return this.getUserById(id).getUsername(); }
}
