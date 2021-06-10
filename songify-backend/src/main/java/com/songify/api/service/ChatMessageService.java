package com.songify.api.service;

import com.songify.api.model.chat.ChatMessage;
import com.songify.api.model.chat.MessageStatus;

import java.util.List;

public interface ChatMessageService {
    ChatMessage save(ChatMessage chatMessage);

    long countNewMessages(Long senderId, Long recipientId);

    List<ChatMessage> findChatMessages(Long senderId, Long recipientId);

    ChatMessage findById(Long id);

    void updateStatuses(Long senderId, Long recipientId, MessageStatus status);
}
