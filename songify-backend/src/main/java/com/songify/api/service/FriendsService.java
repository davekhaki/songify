package com.songify.api.service;

import com.songify.api.model.FriendRequest;
import com.songify.api.model.dto.AcceptFriendRequestRequest;
import com.songify.api.model.dto.SendFriendRequestRequest;

import java.util.List;

public interface FriendsService {
    List<FriendRequest> getRequestsByUserId(Long id);

    FriendRequest createRequest(SendFriendRequestRequest request);

    void acceptRequest(AcceptFriendRequestRequest request);
}
