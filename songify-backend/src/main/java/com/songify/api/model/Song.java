package com.songify.api.model;

import javax.persistence.*;

@Entity
@Table(name = "songs")
public class Song {
    /*
    Since songs are retrieved from the spotify api only the id from spotify is important to store
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "spotifyId")
    private String spotifyId;

    public Song() {
    }

    public Song(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public Song(long id, String spotifyId) {
        this.id = id;
        this.spotifyId = spotifyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }
}
