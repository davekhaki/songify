package com.songify.api.repository;

import com.songify.api.model.chat.ChatMessage;
import com.songify.api.model.chat.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    long countBySenderIdAndRecipientIdAndStatus(Long senderId, Long recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

    List<ChatMessage> findAllBySenderIdAndRecipientId(Long senderId, Long recipientId);
}
