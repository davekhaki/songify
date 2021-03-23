package com.songify.api;

import com.songify.api.model.FriendRequest;
import com.songify.api.model.Role;
import com.songify.api.model.User;
import com.songify.api.repository.FriendRequestRepository;
import com.songify.api.repository.RoleRepository;
import com.songify.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Override
    public void run(String... args) throws Exception{
        var adminRole = new Role("Admin");
        var userRole = new Role("User");

        this.roleRepository.save(userRole);
        this.roleRepository.save(adminRole);

        var user1 = new User("test@gmail.com", "testusername", "098f6bcd4621d373cade4e832627b4f6", adminRole);
        var user2 = new User("test2@gmail.com", "realusername", "b1f4f9a523e36fd969f4573e25af4540", userRole);

        this.userRepository.save(user1);
        this.userRepository.save(user2);


    }
}
