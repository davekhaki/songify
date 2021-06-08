package com.songify.api.model;

public class SimpleFriend {
    private String friendName;

    public SimpleFriend(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}
