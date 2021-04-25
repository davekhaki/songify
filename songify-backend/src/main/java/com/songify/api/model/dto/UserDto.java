package com.songify.api.model.dto;

import com.songify.api.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String email;
    private Role role;
}
