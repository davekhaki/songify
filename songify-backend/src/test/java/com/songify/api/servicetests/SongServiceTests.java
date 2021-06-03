package com.songify.api.servicetests;

import com.songify.api.model.Song;
import com.songify.api.service.SongService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class SongServiceTests {

    @Autowired
    private SongService songService;

    @Test
    void addSongTest(){
        Song song = songService.addSong("000SONG");

        Assertions.assertEquals("000SONG", song.getSpotifyId());
    }

    @Test
    void findBySpotifyId(){
        songService.addSong("000HKKAK");
        Song song = songService.findBySpotifyId("000HKKAK");

        Assertions.assertEquals("000HKKAK", song.getSpotifyId() );
    }
}
