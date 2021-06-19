package com.songify.api.servicetests;

import com.songify.api.model.Playlist;
import com.songify.api.model.Song;
import com.songify.api.model.dto.NewPlaylistRequest;
import com.songify.api.repository.PlaylistRepository;
import com.songify.api.repository.SongRepository;
import com.songify.api.service.PlaylistService;
import com.songify.api.service.SongService;
import com.songify.api.service.impl.PlaylistServiceImpl;
import com.songify.api.service.impl.SongServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PlaylistServiceTests {

    PlaylistRepository playlistRepository;
    PlaylistService playlistService;
    SongRepository songRepository;
    SongService songService;
    List<Playlist> playlists;

    @BeforeEach
    void createTestData(){
        this.playlistRepository = Mockito.mock(PlaylistRepository.class);
        this.songRepository = Mockito.mock(SongRepository.class);
        this.songService = new SongServiceImpl(songRepository);
        this.playlistService = new PlaylistServiceImpl(playlistRepository, songService);

        this.playlists = new ArrayList<>();
        playlists.add(new Playlist("first playlist", "desc", 3));
        playlists.add(new Playlist("second playlist", "desc", 1));
        playlists.add(new Playlist("third playlist", "desc", 2));
    }

    @Test
    void getPlaylistsTest(){
        Mockito.when(playlistRepository.findAll()).thenReturn(playlists);
        List<Playlist> actual = playlistService.getPlaylists();

        Assertions.assertEquals(playlists, actual);
    }

    @Test
    void getPlaylistByIdTest(){
        Mockito.when(playlistRepository.findById(1L)).thenReturn(Optional.of(playlists.get(1)));

        Playlist playlist = playlistService.getPlaylistById(1L);

        Assertions.assertEquals("second playlist", playlist.getTitle());
    }

    @Test
    void getPlaylistByTitleTrueTest(){
        Mockito.when(playlistRepository.getPlaylistByTitle("first playlist")).thenReturn(playlists.get(0));

        boolean titleExists = playlistService.getPlaylistByTitle("first playlist");

        Assertions.assertTrue(titleExists);
    }

    @Test
    void getPlaylistByTitleFalseTest(){
        Mockito.when(playlistRepository.getPlaylistByTitle("dwadwaGWEGALKJWLAFK")).thenReturn(null);

        boolean titleExists = playlistService.getPlaylistByTitle("dwadwaGWEGALKJWLAFK");

        Assertions.assertFalse(titleExists);
    }

    @Test
    void getPlaylistsByUsernameTest(){


        List<Playlist> actual = playlistService.getPlaylistsByUsername("username!");
    }

//    @Test
//    void createPlaylistTest(){
//        Playlist playlist = playlistService.addPlaylist(new NewPlaylistRequest("playlist name", "description!", "username"));
//
//        Assertions.assertEquals(Playlist.class, playlist.getClass());
//        Assertions.assertEquals("playlist name", playlist.getTitle());
//    }
//
//    @Test
//    void createPlaylistWithExistingNameTest(){
//        Playlist playlist = playlistService.addPlaylist(new NewPlaylistRequest("playlist name", "description!", "username"));
//
//        Assertions.assertNull(playlist);
//    }
//
//    @Test
//    void getPlaylistsTest(){
//        List<Playlist> playlists = playlistService.getPlaylists();
//
//        Assertions.assertEquals("playlist name", playlists.get(0).getTitle());
//    }
//
//    @Test
//    void getPlaylistByIdTest(){
//        Playlist playlist = playlistService.getPlaylistById(1L);
//
//        Assertions.assertNotNull(playlist);
//    }
//
//    @Test
//    void getPlaylistByTitleTest(){
//        boolean exists = playlistService.getPlaylistByTitle("playlist name");
//
//        Assertions.assertTrue(exists);
//    }
//
//    @Test
//    void getPlaylistByTitleFailTest(){
//        boolean exists = playlistService.getPlaylistByTitle("85832FHWAKHF7727AA;;;;;");
//
//        Assertions.assertFalse(exists);
//    }
//
//    @Test
//    void getPlaylistByUsernameTest(){
//        List<Playlist> playlist = playlistService.getPlaylistsByUsername("username");
//
//        Assertions.assertEquals(1L, playlist.get(0).getId());
//    }
//
//    @Test
//    void deletePlaylist(){
//        Playlist playlist = playlistService.addPlaylist(new NewPlaylistRequest("temp", "temp", "temp"));
//        String message = playlistService.deletePlaylist(playlist.getId());
//
//        Assertions.assertEquals("Success", message);
//    }
//
//    @Test
//    void addSongToPlaylistTest(){
//        Song song = songRepository.save(new Song("12345"));
//        Song addedSong = playlistService.addSongToPlaylist(1L, "12345");
//
//        Assertions.assertEquals(song.getId(), addedSong.getId());
//    }
//
//    @Test
//    void addSongToPlaylistNewSongTest(){
//        String id = "H7B3";
//        Song song = playlistService.addSongToPlaylist(1L, id);
//
//        Assertions.assertEquals(song.getSpotifyId(), id);
//    }
//
//    @Test
//    void getPopularPlaylistsTest(){
//        var playlists = playlistService.getPopularPlaylists();
//
//        Assertions.assertEquals(8L, playlists.getSize());
//        Assertions.assertEquals(1L, playlists.getContent().size());
//    }
}
