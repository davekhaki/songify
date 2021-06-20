package com.songify.api.service;

import com.songify.api.model.Playlist;
import com.songify.api.model.Song;
import com.songify.api.model.dto.NewPlaylistRequest;

import java.util.List;

public interface PlaylistService {
    List<Playlist> getPlaylists();

    Playlist getPlaylistById(Long id);

    boolean getPlaylistByTitle(String title);

    List<Playlist> getPlaylistsByUsername(String username);

    Playlist addPlaylist(NewPlaylistRequest request);

    String deletePlaylist(Long id);

    Song addSongToPlaylist(Long playlistId, String spotifyId);

    String removeSongFromPlaylist(Long playlistId, String spotifyId);
}
