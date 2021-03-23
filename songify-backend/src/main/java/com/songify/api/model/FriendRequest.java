package com.songify.api.model;

import javax.persistence.*;

@Entity
@Table(name="friend_requests")
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private long id;

/*    @OneToOne
    private User sender;

    @OneToOne
    private User receiver;*/

    public FriendRequest() {
    }

/*    public FriendRequest(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

/*    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }*/
}
