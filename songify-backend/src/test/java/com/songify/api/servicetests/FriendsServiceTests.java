package com.songify.api.servicetests;

import com.songify.api.model.FriendRequest;
import com.songify.api.model.Role;
import com.songify.api.model.User;
import com.songify.api.model.dto.SendFriendRequestRequest;
import com.songify.api.service.FriendsService;
import com.songify.api.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class FriendsServiceTests {

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private UserService userService;

    @Test
    void findByUserIdRequests(){
        User user = new User("email", "username", "password", new Role());
        user.setId(605L);
        userService.save(user);
        friendsService.createRequest(new SendFriendRequestRequest(605L, 605L));
        List<FriendRequest> requests = friendsService.getRequestsByUserId(605L);

        Assertions.assertEquals(605L, requests.get(0).getReceiverId());
    }

//    @Test
//    void acceptRequestTest(){
//        User user = new User("email", "username", "password", new Role());
//        user.setId(606L);
//        userService.save(user);
//        User user2 = new User("email", "six0seven", "password", new Role());
//        user2.setId(607L);
//        userService.save(user2);
//        friendsService.createRequest(new SendFriendRequestRequest(606L, 607L));
//        friendsService.acceptRequest(new AcceptFriendRequestRequest(606L, 607L));
//
//        User assertable = userService.getUserById(606L);
//
//        String friendName = assertable.getFriends().toString();
//
//        Assertions.assertEquals(1, userService.getUserById(606L).getFriends().size());
//    }
}
