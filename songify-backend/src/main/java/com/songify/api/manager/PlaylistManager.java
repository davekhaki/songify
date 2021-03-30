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
        var song = songRepository.findById(song_id);
        var playlist = playlistRepository.findById(playlist_id);

        playlist.get().addSong(song.get());
        playlistRepository.save(playlist.get());

        return playlist.get().addSong(song.get());

    }
}
