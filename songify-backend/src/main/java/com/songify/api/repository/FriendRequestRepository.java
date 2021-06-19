package com.songify.api.repository;

import com.songify.api.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findAllByReceiverId(Long receiverId);

    FriendRequest findByReceiverIdAndSenderId(Long receiverId, Long senderId);
}
