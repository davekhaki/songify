package com.songify.api.manager;

import com.songify.api.exceptions.UserNotFoundException;
import com.songify.api.model.User;
import com.songify.api.repository.RoleRepository;
import com.songify.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    public ResponseEntity<User> getUserById(Long id) throws UserNotFoundException{
        var user = this.userRepository.findById(id);

        if(user.isPresent()) return new ResponseEntity<>(user.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<User> addUser(User u){
        //set the role for the user
        u.setRole(roleRepository.findByName(u.getRole().getName()));
        //save the user
        this.userRepository.save(u);
        return new ResponseEntity<>(u, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<User> updateUser(Long userId, User updatedUser)
    {
        //find user
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        //update the user details
        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getUsername());
        user.setPasshash(updatedUser.getPasshash());
        //no change role for now

        //save new user details and return updated user and OK http
        final User updated = userRepository.save(user);
        return ResponseEntity.ok(updated);
    }

    public ResponseEntity deleteUser(Long id) {
        //find user with given id
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        //delete user
        userRepository.delete(user);

        //return successful deletion
        return ResponseEntity.ok("Success");
    }
}