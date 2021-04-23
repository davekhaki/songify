package com.songify.api.service;

import com.songify.api.model.dto.UserDto;
import com.songify.api.exceptions.UserNotFoundException;
import com.songify.api.model.User;
import com.songify.api.repository.RoleRepository;
import com.songify.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    public ResponseEntity<User> getUserById(Long id) throws UserNotFoundException{
        var user = this.userRepository.findById(id);

        if(user.isPresent()) return new ResponseEntity<>(user.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<User> addUser(UserDto Dto){
        User actual = new User();

        actual.setEmail(Dto.getEmail());
        actual.setUsername(Dto.getUsername());
        actual.setPassword(passwordEncoder.encode(Dto.getPassword()));
        actual.setRole(roleRepository.findByName(Dto.getRole().getName()));

        //save the user
        this.userRepository.save(actual);
        return new ResponseEntity<>(actual, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<User> updateUser(Long userId, UserDto Dto)
    {
        //find user
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        //update the user details
        user.setEmail(Dto.getEmail());
        user.setUsername(Dto.getUsername());
        user.setPassword(Dto.getPassword());
        //no change role for now

        //save new user details and return updated user and OK http
        final User updated = userRepository.save(user);
        return ResponseEntity.ok(updated);
    }

    public ResponseEntity<String> deleteUser(Long id) {
        //find user with given id
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        //delete user
        userRepository.delete(user);

        //return successful deletion
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public User readUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

}