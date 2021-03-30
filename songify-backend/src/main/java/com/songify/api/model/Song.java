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

    @Column(name = "spotify_id")
    private int spotify_id;

    public Song() {
    }

    public Song(int spotify_id) {
        this.spotify_id = spotify_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSpotify_id() {
        return spotify_id;
    }

    public void setSpotify_id(int spotify_id) {
        this.spotify_id = spotify_id;
    }
}
