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
import org.springframework.boot.test.context.SpringBootTest;

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
        Mockito.when(playlistRepository.getPlaylistByCreatedBy("username!")).thenReturn(playlists);

        List<Playlist> actual = playlistService.getPlaylistsByUsername("username!");

        Assertions.assertIterableEquals(playlists, actual);
    }

    @Test
    void createPlaylistTest(){
        Playlist newPlaylist = new Playlist("playlist name", "description!", 0);
        newPlaylist.setCreatedBy("username");
        newPlaylist.setLastModifiedBy("username");

        Mockito.when(playlistRepository.getPlaylistByTitle("playlist name")).thenReturn(null);
        Mockito.when(playlistRepository.save(Mockito.any())).thenReturn(newPlaylist);

        Playlist playlist = playlistService.addPlaylist(new NewPlaylistRequest("playlist name", "description!", "username"));

        Assertions.assertEquals(Playlist.class, playlist.getClass());
        Assertions.assertEquals("playlist name", playlist.getTitle());
    }

    @Test
    void createPlaylistWithExistingNameTest(){
        Mockito.when(playlistRepository.getPlaylistByTitle("playlist name")).thenReturn(playlists.get(0));

        Playlist playlist = playlistService.addPlaylist(new NewPlaylistRequest("playlist name", "description!", "username"));

        Assertions.assertNull(playlist);
    }

    @Test
    void deletePlaylist(){
        Mockito.when(playlistRepository.findById(2L)).thenReturn(Optional.of(playlists.get(0)));
        String message = playlistService.deletePlaylist(2L);

        Assertions.assertEquals("Success", message);
    }

    @Test
    void addSongToPlaylistTest(){
        Mockito.when(songRepository.findBySpotifyId("12345")).thenReturn(new Song("12345"));
        Mockito.when(playlistRepository.findById(1L)).thenReturn(Optional.of(playlists.get(0)));

        Song song = playlistService.addSongToPlaylist(1L, "12345");

        Assertions.assertEquals("12345", song.getSpotifyId());
    }

    @Test
    void addSongToPlaylistNewSongTest(){
        Song actual = new Song("123");
        Mockito.when(songRepository.findBySpotifyId(actual.getSpotifyId())).thenReturn(null);
        Mockito.when(songRepository.save(Mockito.any(Song.class))).thenReturn(actual);
        Mockito.when(playlistRepository.findById(1L)).thenReturn(Optional.of(playlists.get(0)));

        Song song = playlistService.addSongToPlaylist(1L, "123");

        Assertions.assertEquals("123", song.getSpotifyId());
    }

    @Test
    void removeSongFromPlaylist(){
        Song song = new Song("99");
        playlists.get(0).addSong(song);
        Mockito.when(songRepository.findBySpotifyId("99")).thenReturn(song);
        Mockito.when(playlistRepository.findById(1L)).thenReturn(Optional.of(playlists.get(0)));

        String message = playlistService.removeSongFromPlaylist(1L, "99");

        Assertions.assertEquals("Success", message);
    }

    @Test
    void removeSongNotInDbFromPlaylistTest(){
        Song song = new Song("99");
        playlists.get(0).addSong(song);
        Mockito.when(songRepository.findBySpotifyId("99")).thenReturn(null);
        Mockito.when(songRepository.save(Mockito.any(Song.class))).thenReturn(song);
        Mockito.when(playlistRepository.findById(1L)).thenReturn(Optional.of(playlists.get(0)));

        String message = playlistService.removeSongFromPlaylist(1L, "99");
        Assertions.assertEquals("Success", message);
    }

    @Test
    void removeSongNotInPlaylistTest(){
        Mockito.when(songRepository.findBySpotifyId("99")).thenReturn(null);
        Mockito.when(songRepository.save(Mockito.any(Song.class))).thenReturn(new Song("99"));
        Mockito.when(playlistRepository.findById(1L)).thenReturn(Optional.of(playlists.get(0)));

        String message = playlistService.removeSongFromPlaylist(1L, "99");
        Assertions.assertEquals("Success", message);
    }
}
