package com.songify.api.model.dto;

import com.songify.api.model.Role;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private Role role;
}
