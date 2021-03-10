package com.songify.api;

import com.songify.api.model.Role;
import com.songify.api.model.User;
import com.songify.api.repository.RoleRepository;
import com.songify.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception{
        var adminRole = new Role("Admin");
        var userRole = new Role("User");

        this.roleRepository.save(userRole);
        this.roleRepository.save(adminRole);

        this.userRepository.save(new User("test@gmail.com", "testusername", "098f6bcd4621d373cade4e832627b4f6", adminRole));
        this.userRepository.save(new User("test2@gmail.com", "realusername", "b1f4f9a523e36fd969f4573e25af4540", userRole));
    }
}
