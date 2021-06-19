package com.songify.api.servicetests;

import com.songify.api.model.Song;
import com.songify.api.repository.SongRepository;
import com.songify.api.service.SongService;
import com.songify.api.service.impl.SongServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SongServiceTests {

    private SongService songService;
    private SongRepository songRepository;
    private List<Song> songs;

    @BeforeEach
    void createTestData(){
        this.songRepository = Mockito.mock(SongRepository.class);
        this.songService = new SongServiceImpl(songRepository);

        this.songs = new ArrayList<>();
        songs.add(new Song("000SONG"));
        songs.add(new Song("001SONG"));
        songs.add(new Song("002SONG"));
    }

    @Test
    void findBySpotifyIdTest(){
        Mockito.when(songRepository.findBySpotifyId("000SONG")).thenReturn(songs.get(0));

        Assertions.assertEquals(0L, songService.findBySpotifyId("000SONG").getId());
    }

    @Test
    void addSongTest(){
        Song newSong = new Song("new");
        Mockito.when(songRepository.save(newSong)).thenReturn(newSong);

        Song song = songService.addSong("new");

        Assertions.assertEquals("new", newSong.getSpotifyId());
    }
}
