package com.songify.api.manager;

import com.songify.api.model.Playlist;
import com.songify.api.model.Song;
import com.songify.api.repository.PlaylistRepository;
import com.songify.api.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Song addSong(Long playlist_id, Long song_id){
        //determine song based on given id
        var song = songRepository.findById(song_id);
        //determine playlist based on given id
        var playlist = playlistRepository.findById(playlist_id);

        //get playlist and add song to it
        playlist.get().addSong(song.get());
        //save the updated playlist
        playlistRepository.save(playlist.get());

        //return the added song
        return playlist.get().addSong(song.get());

    }
}
