package com.songify.api.controller;

import com.songify.api.model.dto.NewPlaylistRequest;
import com.songify.api.service.PlaylistService;
import com.songify.api.model.Playlist;
import com.songify.api.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping("/{username}")
    public List<Playlist> getPlaylistsByUser(@PathVariable String username){
        return playlistService.getPlaylistsByUsername(username);
    }

    @PostMapping("")
    public ResponseEntity<Playlist> addPlaylist(@RequestBody NewPlaylistRequest newPlaylist) {
        return new ResponseEntity<>(this.playlistService.addPlaylist(newPlaylist), HttpStatus.OK);
    }

    @GetMapping("/popular")
    public Page<Playlist> getPopularPlaylists() {return this.playlistService.getPopularPlaylists(); }

    @PostMapping("/add/{playlist}/{song}")
    public ResponseEntity<Song> addSongToPlaylist(@PathVariable Long playlist, @PathVariable Long song){
        return playlistService.addSong(playlist, song);
    }
}
