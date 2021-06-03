package com.songify.api.servicetests;

import com.songify.api.model.Playlist;
import com.songify.api.model.Song;
import com.songify.api.model.dto.NewPlaylistRequest;
import com.songify.api.repository.SongRepository;
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

    @Autowired
    SongRepository songRepository;

    @Test
    void createPlaylistTest(){
        Playlist playlist = playlistService.addPlaylist(new NewPlaylistRequest("playlist name", "description!", "username"));

        Assertions.assertEquals(Playlist.class, playlist.getClass());
        Assertions.assertEquals("playlist name", playlist.getTitle());
    }

    @Test
    void createPlaylistWithExistingNameTest(){
        Playlist playlist = playlistService.addPlaylist(new NewPlaylistRequest("playlist name", "description!", "username"));

        Assertions.assertNull(playlist);
    }

    @Test
    void getPlaylistsTest(){
        List<Playlist> playlists = playlistService.getPlaylists();

        Assertions.assertEquals("playlist name", playlists.get(0).getTitle());
    }

    @Test
    void getPlaylistByIdTest(){
        Playlist playlist = playlistService.getPlaylistById(1L);

        Assertions.assertNotNull(playlist);
    }

    @Test
    void getPlaylistByTitleTest(){
        boolean exists = playlistService.getPlaylistByTitle("playlist name");

        Assertions.assertTrue(exists);
    }

    @Test
    void getPlaylistByTitleFailTest(){
        boolean exists = playlistService.getPlaylistByTitle("85832FHWAKHF7727AA;;;;;");

        Assertions.assertFalse(exists);
    }

    @Test
    void getPlaylistByUsernameTest(){
        List<Playlist> playlist = playlistService.getPlaylistsByUsername("username");

        Assertions.assertEquals(playlist.get(0).getId(), 1L);
    }

    @Test
    void deletePlaylist(){
        Playlist playlist = playlistService.addPlaylist(new NewPlaylistRequest("temp", "temp", "temp"));
        String message = playlistService.deletePlaylist(playlist.getId());

        Assertions.assertEquals(message, "Success");
    }

    @Test
    void addSongToPlaylistTest(){
        Song song = songRepository.save(new Song("12345"));
        Song addedSong = playlistService.addSongToPlaylist(1L, "12345");

        Assertions.assertEquals(song.getId(), addedSong.getId());
    }

    // TODO: get popular playlists test
}
