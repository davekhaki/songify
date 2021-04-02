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
    private int spotifyId;

    public Song() {
    }

    public Song(int spotify_id) {
        this.spotifyId = spotify_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(int spotify_id) {
        this.spotifyId = spotify_id;
    }
}
