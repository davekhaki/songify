package com.songify.api;

import com.songify.api.manager.PlaylistManager;
import com.songify.api.model.*;
import com.songify.api.repository.RoleRepository;
import com.songify.api.repository.SongRepository;
import com.songify.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

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
    private PlaylistManager playlistManager;

    @Autowired
    private SongRepository songRepository;

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

        var song1 = new Song(2);
        var song2 = new Song(3);

        this.songRepository.save(song1);
        this.songRepository.save(song2);

        var playlist1 = new Playlist("playlistONE", new ArrayList<>(), 30);
        var playlist2 = new Playlist("ANOTHER PLAYLIST", new ArrayList<>(), 0);


        playlist1.addSong(song1);
        playlist1.addSong(song2);

        this.playlistManager.savePlaylist(playlist1);
        this.playlistManager.savePlaylist(playlist2);


    }
}
