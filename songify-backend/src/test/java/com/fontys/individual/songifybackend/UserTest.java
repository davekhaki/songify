package com.fontys.individual.songifybackend;

import com.fontys.individual.songifybackend.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void UserEmailTest(){
        User user = new User("email@gmail.com", "name", "75hahgh30");

        String toCompare = user.getEmail();

        assertEquals("email@gmail.com", toCompare);
    }
}
