package com.songify.api.servicetests;

import com.songify.api.model.Playlist;
import com.songify.api.model.dto.NewPlaylistRequest;
import com.songify.api.service.PlaylistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class PlaylistServiceTests {

    @Autowired
    PlaylistService playlistService;

    @Test
    void createPlaylistTest(){
        Playlist playlist = playlistService.addPlaylist(new NewPlaylistRequest("playlist name", "description!"));

        Assertions.assertEquals(Playlist.class, playlist.getClass());
        Assertions.assertEquals("playlist name", playlist.getTitle());
    }

    @Test
    void getPlaylistsTest(){
        List<Playlist> playlists = playlistService.getPlaylists();

        Assertions.assertEquals("playlist name", playlists.get(0).getTitle());
    }

    // TODO: add song to playlist test

    // TODO: get popular playlists test
}
