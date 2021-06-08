package com.songify.api.service;

import com.songify.api.model.dto.AcceptFriendRequestRequest;
import com.songify.api.model.dto.UserDto;
import com.songify.api.exceptions.UserNotFoundException;
import com.songify.api.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id) throws UserNotFoundException;

    User getUserByUsername(String username);

    User getUserByUsernameAndPassword(String username, String password);

    User addUser(UserDto dto);

    User updateUser(Long userId, UserDto dto);

    String deleteUser(Long id);

    User save(User user);
}