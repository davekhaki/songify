package com.fontys.individual.songifybackend;

import com.fontys.individual.songifybackend.model.Role;
import com.fontys.individual.songifybackend.model.User;
import com.fontys.individual.songifybackend.repository.RoleRepository;
import com.fontys.individual.songifybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SongifyBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SongifyBackendApplication.class, args);
    }

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        this.roleRepository.save(new Role("User"));
        this.roleRepository.save(new Role("DJ"));
        this.roleRepository.save(new Role("Admin"));

        this.userRepository.save(new User("test@gmail.com", "testusername", "098f6bcd4621d373cade4e832627b4f6"));
        this.userRepository.save(new User("test2@gmail.com", "realusername", "b1f4f9a523e36fd969f4573e25af4540"));

    }
}
