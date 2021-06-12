package com.songify.api.servicetests;

import com.songify.api.model.chat.ChatMessage;
import com.songify.api.model.chat.MessageStatus;
import com.songify.api.service.ChatMessageService;
import com.songify.api.service.ChatRoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class ChatMessageServiceTests {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Test
    void findByIdTest(){
        chatMessageService.save(new ChatMessage()); //id is auto generated
        chatMessageService.save(new ChatMessage());
        Assertions.assertNotNull(chatMessageService.findById(2L));
    }

    @Test
    void saveTest(){
        ChatMessage message = new ChatMessage(45L, "CHAT ID", 3L, 4L, "sender", "receiver", "hello", new Date(), MessageStatus.RECEIVED);
        ChatMessage found = chatMessageService.save(message);
        Assertions.assertEquals("hello", found.getContent());
    }

    @Test
    void countNewMessagesTest(){
        chatMessageService.save(new ChatMessage(101L, "CHAT ID", 33L, 34L, "sender", "receiver", "hello", new Date(), MessageStatus.RECEIVED));
        chatMessageService.save(new ChatMessage(102L, "CHAT ID", 33L, 34L, "sender", "receiver", "hello", new Date(), MessageStatus.RECEIVED));
        chatMessageService.save(new ChatMessage(103L, "CHAT ID", 33L, 34L, "sender", "receiver", "hello", new Date(), MessageStatus.RECEIVED));
        chatMessageService.save(new ChatMessage(104L, "CHAT ID", 33L, 34L, "sender", "receiver", "hello", new Date(), MessageStatus.RECEIVED));
        Assertions.assertEquals(4L, chatMessageService.countNewMessages(33L, 34L));
    }

    @Test
    void findChatMessagesTest(){
        Optional<String> chatId = chatRoomService.getChatId(77L, 78L, true);
        chatMessageService.save(new ChatMessage(200L, "77_78", 77L, 78L, "sender", "receiver", "hi", new Date(), MessageStatus.RECEIVED));
        List<ChatMessage> messages = chatMessageService.findChatMessages(77L, 78L);
        Assertions.assertEquals("hi", messages.get(0).getContent());

    }
}
