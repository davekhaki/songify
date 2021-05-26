package com.songify.api.service;

import com.songify.api.model.Playlist;
import com.songify.api.model.Song;
import com.songify.api.model.dto.NewPlaylistRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PlaylistService {
    List<Playlist> getPlaylists();

    Playlist getPlaylistById(Long id);

    List<Playlist> getPlaylistsByUsername(String username);

    Playlist addPlaylist(NewPlaylistRequest request);

    Page<Playlist> getPopularPlaylists();

    Song addSongToPlaylist(Long playlistId, Long songId);
}
