package com.songify.api.manager;

import com.songify.api.model.User;
import com.songify.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> tryLogin(String username, String pass_hash){
        var user = this.userRepository.findByUsernameAndPasshash(username, pass_hash);
        if(user != null){ return new ResponseEntity<>(user, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
