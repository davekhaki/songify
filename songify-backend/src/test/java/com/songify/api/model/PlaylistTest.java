package com.songify.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class PlaylistTest {

    @Test
    void emptyConstructorTest(){
        Playlist playlist = new Playlist();
        assertNull(playlist.getTitle());
    }
}
