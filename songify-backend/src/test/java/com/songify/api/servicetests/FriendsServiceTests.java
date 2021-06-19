package com.songify.api.servicetests;

import com.songify.api.model.FriendRequest;
import com.songify.api.model.Role;
import com.songify.api.model.User;
import com.songify.api.model.dto.AcceptFriendRequestRequest;
import com.songify.api.model.dto.SendFriendRequestRequest;
import com.songify.api.repository.FriendRequestRepository;
import com.songify.api.repository.UserRepository;
import com.songify.api.service.FriendsService;
import com.songify.api.service.UserService;
import com.songify.api.service.impl.FriendsServiceImpl;
import com.songify.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class FriendsServiceTests {

    FriendRequestRepository friendRequestRepository;
    FriendsService friendsService;
    UserRepository userRepository;
    UserService userService;
    List<FriendRequest> requests;

    @BeforeEach
    void createTestData(){
        this.friendRequestRepository = Mockito.mock(FriendRequestRepository.class);
        this.userRepository = Mockito.mock(UserRepository.class);
        this.userService = new UserServiceImpl(userRepository);
        this.friendsService = new FriendsServiceImpl(friendRequestRepository, userService);

        requests = new ArrayList<>();
        requests.add(new FriendRequest(3L, 5L));
        requests.add(new FriendRequest(4L, 5L));
        requests.add(new FriendRequest(6L, 5L));
        requests.add(new FriendRequest(6L, 3L));
        requests.add(new FriendRequest(10L, 15L));
    }

    @Test
    void findRequestsByReceiverIdTest(){
        Mockito.when(friendRequestRepository.findAllByReceiverId(5L)).thenReturn(requests.stream().limit(3).collect(Collectors.toList()));

        List<FriendRequest> list = friendsService.getRequestsByUserId(5L);

        Assertions.assertEquals(3L, list.size());
    }

    @Test
    void createRequestTest(){
        FriendRequest newRequest = new FriendRequest(10L, 15L);
        Mockito.when(friendRequestRepository.save(newRequest)).thenReturn(newRequest);

        FriendRequest request = friendsService.createRequest(new SendFriendRequestRequest(10L, 15L));

        Assertions.assertEquals(10L, newRequest.getSenderId());
    }

    @Test
    void acceptFriendRequestTest(){
        User user1 = new User("email3", "username3", "password3", new Role()); user1.setId(3L);
        User user2 = new User("email5", "username5", "password5", new Role()); user2.setId(5L);
        Mockito.when(userRepository.findById(3L)).thenReturn(Optional.of(user1));
        Mockito.when(userRepository.findById(5L)).thenReturn(Optional.of(user2));
        Set<User> one = new HashSet<User>(){{ add(user1); }};
        Set<User> two = new HashSet<User>(){{ add(user2); }};
        user1.setFriends(two);
        user2.setFriends(one);
        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        Mockito.when(userRepository.save(user2)).thenReturn(user2);

        friendsService.acceptRequest(new AcceptFriendRequestRequest(3L , 5L));

        Assertions.assertEquals("username5", user1.getFriends().stream().findFirst().get().getUsername());
    }
}
