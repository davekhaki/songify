package com.songify.api.model;

public class SimpleFriend {
    private Long friendId;
    private String friendName;

    public SimpleFriend(Long friendId, String friendName) {
        this.friendId = friendId;
        this.friendName = friendName;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}
