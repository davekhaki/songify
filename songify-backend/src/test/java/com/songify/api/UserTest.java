package com.songify.api;

import com.songify.api.model.Role;
import com.songify.api.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void UserEmailTest(){
        User user = new User("email@gmail.com", "name", "75hahgh30", new Role("role"));

        String toCompare = user.getEmail();

        assertEquals("email@gmail.com", toCompare);
    }
}
