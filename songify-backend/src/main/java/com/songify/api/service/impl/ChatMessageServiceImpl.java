package com.songify.api.service.impl;

import com.songify.api.exceptions.ResourceNotFoundException;
import com.songify.api.model.chat.ChatMessage;
import com.songify.api.model.chat.MessageStatus;
import com.songify.api.repository.ChatMessageRepository;
import com.songify.api.service.ChatMessageService;
import com.songify.api.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    @Override
    public long countNewMessages(Long senderId, Long recipientId) {
        return chatMessageRepository.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    @Override
    public List<ChatMessage> findChatMessages(Long senderId, Long recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        var messages = chatId.map(cId -> chatMessageRepository.findByChatId(cId)).orElse(new ArrayList<>());

        if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }
        return messages;
    }

    @Override
    public ChatMessage findById(Long id) {
        return chatMessageRepository.findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return chatMessageRepository.save(chatMessage);
                }).orElseThrow(() -> new ResourceNotFoundException("can't find message (" + id + ")"));
    }

    @Override
    public void updateStatuses(Long senderId, Long recipientId, MessageStatus status) {
        var list = chatMessageRepository.findAllBySenderIdAndRecipientId(senderId, recipientId);
        for (int i = 0; i < list.size(); i++){
            list.get(i).setStatus(status);
        }
    }
}
