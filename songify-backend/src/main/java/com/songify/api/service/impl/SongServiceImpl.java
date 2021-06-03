package com.songify.api.service.impl;

import com.songify.api.model.Song;
import com.songify.api.repository.SongRepository;
import com.songify.api.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    @Override
    public Song findBySpotifyId(String spotifyId){
        return songRepository.findBySpotifyId(spotifyId);
    }

    @Override
    public Song addSong(String spotifyId) {
        return songRepository.save(new Song(spotifyId));
    }
}
