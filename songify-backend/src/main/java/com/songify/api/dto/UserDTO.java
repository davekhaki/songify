package com.songify.api.dto;

import com.songify.api.model.Role;

public class UserDTO {
    Long dtoId;
    String dtoEmail;
    String dtoUsername;
    String dtoPassHash;
    Role dtoRole;

    public UserDTO(String email, String username, String passHash, Role role) {
        this.dtoEmail = email;
        this.dtoUsername = username;
        this.dtoPassHash = passHash;
        this.dtoRole = role;
    }

    public Long getId() {
        return dtoId;
    }

    public void setId(Long id) {
        this.dtoId = id;
    }

    public String getEmail() {
        return dtoEmail;
    }

    public void setEmail(String email) {
        this.dtoEmail = email;
    }

    public String getUsername() {
        return dtoUsername;
    }

    public void setUsername(String username) {
        this.dtoUsername = username;
    }

    public String getPassHash() {
        return dtoPassHash;
    }

    public void setPassHash(String passHash) {
        this.dtoPassHash = passHash;
    }

    public Role getRole() {
        return dtoRole;
    }

    public void setRole(Role role) {
        this.dtoRole = role;
    }
}
