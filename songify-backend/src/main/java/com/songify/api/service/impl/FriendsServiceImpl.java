package com.songify.api.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.songify.api.model.FriendRequest;
import com.songify.api.model.SimpleFriend;
import com.songify.api.model.User;
import com.songify.api.model.dto.AcceptFriendRequestRequest;
import com.songify.api.model.dto.SendFriendRequestRequest;
import com.songify.api.repository.FriendRequestRepository;
import com.songify.api.service.FriendsService;
import com.songify.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FriendsServiceImpl extends JsonSerializer<Set<User>> implements FriendsService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserService userService;

    @Override
    public List<FriendRequest> getRequestsByUserId(Long id){
        return this.friendRequestRepository.findAllByReceiverId(id);
    }

    @Override
    public FriendRequest createRequest(SendFriendRequestRequest request) {
        FriendRequest real = new FriendRequest(request.getSenderId(), request.getReceiverId());
        return this.friendRequestRepository.save(real);
    }

    @Override
    public void acceptRequest(AcceptFriendRequestRequest request) {
        User user1 = userService.getUserById(request.getFirstUserId());
        User user2 = userService.getUserById(request.getSecondUserId());

        user1.addFriend(user2);
        user2.addFriend(user1);

        userService.save(user1);
        userService.save(user2);
    }

    @Override // custom serialization prevent json loop with friends inside of friends inside of friends etc
    public void serialize(Set<User> users, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Set<SimpleFriend> simpleFriends = new HashSet<>();

        users.forEach(user -> simpleFriends.add(new SimpleFriend(user.getUsername())));

        jsonGenerator.writeObject(simpleFriends);
    }
}
