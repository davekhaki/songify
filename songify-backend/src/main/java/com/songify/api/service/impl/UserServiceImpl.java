package com.songify.api.service.impl;

import com.songify.api.exceptions.UserNotFoundException;
import com.songify.api.model.User;
import com.songify.api.model.dto.UserDto;
import com.songify.api.repository.UserRepository;
import com.songify.api.service.RoleService;
import com.songify.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

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
        if(passwordEncoder.matches(password, passwordEncoder.encode(password))){
            return getUserByUsername(username);
        }
        else return null;
    }

    @Override
    public User addUser(UserDto dto) { //TODO: add automatic mapper ?
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

        user.setEmail(dto.getEmail()); //update the user details
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        //no change role for now

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
    public void addFriendship(Long firstUserId, Long secondUserId) {

    }
}
