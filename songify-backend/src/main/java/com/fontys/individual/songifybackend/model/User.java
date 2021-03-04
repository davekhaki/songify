package com.fontys.individual.songifybackend.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "passhash")
    private String passhash;

    public User() {
    }

    public User(String email, String username, String passhash) {
        super();
        this.email = email;
        this.username = username;
        this.passhash = passhash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass_hash() {
        return passhash;
    }

    public void setPass_hash(String passhash) {
        this.passhash = passhash;
    }
}
