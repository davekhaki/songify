package com.songify.api.service;

import com.songify.api.model.Song;

public interface SongService {
    Song findBySpotifyId(String spotifyId);

    Song addSong(String spotifyId);
}
