package com.songify.api.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.songify.api.model.SimpleFriend;
import com.songify.api.model.User;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FriendsSerializer extends JsonSerializer<Set<User>> {

    @Override // custom serialization prevent json loop with friends inside of friends inside of friends etc
    public void serialize(Set<User> users, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Set<SimpleFriend> simpleFriends = new HashSet<>();

        users.forEach(user -> simpleFriends.add(new SimpleFriend(user.getUsername())));

        jsonGenerator.writeObject(simpleFriends);
    }
}
