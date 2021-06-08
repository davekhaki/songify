package com.songify.api.controller;

import com.songify.api.model.FriendRequest;
import com.songify.api.model.dto.AcceptFriendRequestRequest;
import com.songify.api.model.dto.SendFriendRequestRequest;
import com.songify.api.service.FriendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/friends")
@RequiredArgsConstructor
public class FriendsController {

    private final FriendsService friendsService;

    @GetMapping("/requests/{id}")
    public List<FriendRequest> getRequestsByUserId(@PathVariable Long id){
        return friendsService.getRequestsByUserId(id);
    }

    @PostMapping()
    public FriendRequest createFriendRequest(@RequestBody SendFriendRequestRequest request){
        return friendsService.createRequest(request);
    }

    @PostMapping("/accept")
    public void acceptFriendRequest(@RequestBody AcceptFriendRequestRequest request){
        friendsService.acceptRequest(request);
    }
}
