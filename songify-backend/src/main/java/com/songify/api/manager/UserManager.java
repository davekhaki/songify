package com.songify.api.manager;

import com.songify.api.exceptions.UserNotFoundException;
import com.songify.api.model.User;
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
}