package com.songify.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlists")
public class Playlist extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch=FetchType.EAGER) // fetch type to prevent LazyInitializationException in addSongToPlaylistTest
    @JoinColumn(name = "id")
    private List<Song> songs;

    @Column(name = "plays")
    private int plays;

    public Playlist() {
    }

    public Playlist(String title, String description, int plays) {
        this.title = title;
        this.description = description;
        this.songs = new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void removeSong(Song song){
        this.songs.remove(song);
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }
}
