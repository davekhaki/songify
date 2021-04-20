package com.songify.api.controller;

import com.songify.api.manager.PlaylistManager;
import com.songify.api.model.Playlist;
import com.songify.api.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistManager playlistManager;

    @GetMapping("")
    public List<Playlist> getPlaylists(){
        return this.playlistManager.getPlaylists();
    }

    @GetMapping("/popular")
    public Page<Playlist> getPopularPlaylists() {return this.playlistManager.getPopularPlaylists(); }

    @PostMapping("/add/{playlist}/{song}")
    public ResponseEntity<Song> addSongToPlaylist(@PathVariable Long playlist, @PathVariable Long song){
        return playlistManager.addSong(playlist, song);
    }
}
