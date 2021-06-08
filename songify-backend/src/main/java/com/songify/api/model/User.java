package com.songify.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.songify.api.service.FriendsSerializer;

import javax.persistence.*;
import java.util.Set;

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

    @JsonIgnore // ignores password in JSON api responses
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "friends")
    @JsonSerialize(using = FriendsSerializer.class) //custom serialization prevents json infinite loop
    private Set<User> friends; // set == list but no duplicates allowed

    public User() {
    }

    public User(String email, String username, String password, Role role) {
        super();
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public Set<User> getFriends() { return friends; }

    public void setFriends(Set<User> friends) { this.friends = friends; }

    public void addFriend(User user){ this.friends.add(user); }
}