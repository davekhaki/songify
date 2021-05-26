package com.songify.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SongTests {

    @Test
    void getAndSetIdTest(){
        Song song = new Song(2);
        song.setId(2L);
        Assertions.assertEquals(2L, song.getId());
    }

    @Test
    void getSpotifyIdTest(){
        Song song = new Song(2);
        Assertions.assertEquals(2, song.getSpotifyId());
    }

    @Test
    void setSpotifyIdTest(){
        Song song = new Song(2);
        song.setSpotifyId(3);
        Assertions.assertEquals(3, song.getSpotifyId());
    }
}
