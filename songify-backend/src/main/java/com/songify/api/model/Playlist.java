package com.songify.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "playlists")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinColumn(name = "id")
    private List<Song> songs;

    @Column(name = "plays")
    private int plays;

    public Playlist() {
    }

    public Playlist(String title, List<Song> songs, int plays) {
        this.title = title;
        this.songs = songs;
        this.plays = plays;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Song addSong(Song song){
        this.songs.add(song);
        return song;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }
}
