package com.songify.api.manager;

import com.songify.api.exceptions.ResourceNotFoundException;
import com.songify.api.model.Playlist;
import com.songify.api.model.Song;
import com.songify.api.repository.PlaylistRepository;
import com.songify.api.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistManager {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SongRepository songRepository;

    public List<Playlist> getPlaylists(){
        return this.playlistRepository.findAll();
    }

    public Playlist savePlaylist(Playlist p){
        return this.playlistRepository.save(p);
    }

    public ResponseEntity<Song> addSong(Long playlistId, Long songId){
        //determine song based on given id
        var song = songRepository.findById(songId).orElseThrow(()-> new ResourceNotFoundException("Adding song to playlist, song not found"));
        //determine playlist based on given id
        var playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new ResourceNotFoundException("playlist not found during add song"));

        playlist.addSong(song);
        playlistRepository.save(playlist);

        //return the added song
        return new ResponseEntity<>(song, HttpStatus.OK);


    }
}
