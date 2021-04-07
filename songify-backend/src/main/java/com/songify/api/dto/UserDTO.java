package com.songify.api.dto;

import com.songify.api.model.Role;

public class UserDTO {
    Long id;
    String email;
    String username;
    String passHash;
    Role role;

    public UserDTO(String email, String username, String passHash, Role role) {
        this.email = email;
        this.username = username;
        this.passHash = passHash;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
