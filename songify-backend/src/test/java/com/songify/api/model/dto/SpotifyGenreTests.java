package com.songify.api.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpotifyGenreTests {

    @Test
    void getIdTest(){
        SpotifyGenre genre = new SpotifyGenre(1, "name", "href", "http.com");
        Assertions.assertEquals(1, genre.getId());
    }

    @Test
    void setIdTest(){
        SpotifyGenre genre = new SpotifyGenre(1, "name", "href", "http.com");
        genre.setId(2);
        Assertions.assertEquals(2, genre.getId());
    }

    @Test
    void getNameTest(){
        SpotifyGenre genre = new SpotifyGenre(1, "name", "href", "http.com");
        Assertions.assertEquals("name", genre.getName());
    }

    @Test
    void setNameTest(){
        SpotifyGenre genre = new SpotifyGenre(1, "name", "href", "http.com");
        genre.setName("new");
        Assertions.assertEquals("new", genre.getName());
    }

    @Test
    void getHrefTest(){
        SpotifyGenre genre = new SpotifyGenre(1, "name", "href", "http.com");
        Assertions.assertEquals("href", genre.getHref());
    }

    @Test
    void setHrefTest(){
        SpotifyGenre genre = new SpotifyGenre(1, "name", "href", "http.com");
        genre.setHref("new");
        Assertions.assertEquals("new", genre.getHref());
    }

    @Test
    void getIconUrlTest(){
        SpotifyGenre genre = new SpotifyGenre(1, "name", "href", "http.com");
        Assertions.assertEquals("http.com", genre.getIconUrl());
    }

    @Test
    void setIconUrlTest(){
        SpotifyGenre genre = new SpotifyGenre(1, "name", "href", "http.com");
        genre.setIconUrl("url.com");
        Assertions.assertEquals("url.com", genre.getIconUrl());
    }
}
