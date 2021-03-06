package com.songify.api.controller;

import com.songify.api.model.dto.NewPlaylistRequest;
import com.songify.api.model.Playlist;
import com.songify.api.model.Song;
import com.songify.api.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping("")
    public List<Playlist> getPlaylists(){
        return this.playlistService.getPlaylists();
    }

    @GetMapping("/id/{id}")
    public Playlist getPlaylistById(@PathVariable Long id){ return this.playlistService.getPlaylistById(id); }

    @GetMapping("/{username}")
    public List<Playlist> getPlaylistsByUser(@PathVariable String username){
        return playlistService.getPlaylistsByUsername(username);
    }

    @PostMapping("")
    public ResponseEntity<Playlist> addPlaylist(@RequestBody NewPlaylistRequest newPlaylist) {
        return new ResponseEntity<>(this.playlistService.addPlaylist(newPlaylist), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String deletePlaylist(@PathVariable Long id){ return this.playlistService.deletePlaylist(id); }

    @PostMapping("/add/{playlist}/{song}")
    public Song addSongToPlaylist(@PathVariable Long playlist, @PathVariable String song){
        return playlistService.addSongToPlaylist(playlist, song);
    }

    @PostMapping("/remove/{playlist}/{song}")
    public String removeSongFromPlaylist(@PathVariable Long playlist, @PathVariable String song){
        return playlistService.removeSongFromPlaylist(playlist, song);
    }
}
