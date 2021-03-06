package com.songify.api.service.impl;

import com.songify.api.exceptions.ResourceNotFoundException;
import com.songify.api.model.Playlist;
import com.songify.api.model.Song;
import com.songify.api.model.dto.NewPlaylistRequest;
import com.songify.api.repository.PlaylistRepository;
import com.songify.api.service.PlaylistService;
import com.songify.api.service.SongService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SongService songService;

    @Override
    public List<Playlist> getPlaylists(){
        return this.playlistRepository.findAll();
    }

    @Override
    public Playlist getPlaylistById(Long id) {
        return this.playlistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Playlist not found with id"));
    }

    @Override
    public boolean getPlaylistByTitle(String title) { //return true if a playlist with specific title already exists
        return this.playlistRepository.getPlaylistByTitle(title) != null;
    }

    @Override
    public List<Playlist> getPlaylistsByUsername(String username){
        return this.playlistRepository.getPlaylistByCreatedBy(username);
    }

    @Override
    public Playlist addPlaylist(NewPlaylistRequest newPlaylist) {
        if(getPlaylistByTitle(newPlaylist.getTitle())){ // future: make this also check username so different users can have same playlist titles
            return null; //prevents playlists from having duplicate titles
        }
        var playlist = new Playlist();

        // CHOSEN VALUES
        playlist.setTitle(newPlaylist.getTitle());
        playlist.setDescription(newPlaylist.getDesc());
        // NEW PLAYLIST AUTOMATIC VALUES
        playlist.setPlays(0);
        playlist.setSongs(new ArrayList<>());
        // AUDITABLE VALUES
        playlist.setCreatedBy(newPlaylist.getUsername());
        playlist.setLastModifiedBy(newPlaylist.getUsername());

        return this.playlistRepository.save(playlist);
    }

    @Override
    public String deletePlaylist(Long id){
        Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);
        Playlist playlist = getPlaylistById(id);
        playlistRepository.delete(playlist);
        logger.info("Playlist delete with ID: " + playlist.getId() + " and name: " + playlist.getTitle());
        return "Success";
    }

    @Override
    public Song addSongToPlaylist(Long playlistId, String spotifyId){
        //determine song based on given id
        Song song = songService.findBySpotifyId(spotifyId);
        if(song == null){ //if the song isn't in the system yet, add it.
            song = songService.addSong(spotifyId);
        }
        //determine playlist based on given id
        var playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new ResourceNotFoundException("playlist not found during add song"));

        playlist.addSong(song);
        playlistRepository.save(playlist);

        //return the added song
        return song;

    }

    @Override
    public String removeSongFromPlaylist(Long playlistId, String spotifyId){
        Song song = songService.findBySpotifyId(spotifyId);
        if(song == null){ song = songService.addSong(spotifyId); }

        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new ResourceNotFoundException("remove playlist, playlist with id not found:" + playlistId));

        if(playlist.getSongs().contains(song)){
            playlist.removeSong(song);
        }
        playlistRepository.save(playlist);
        return("Success");
    }
}
