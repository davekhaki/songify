package com.songify.api.servicetests;

import com.songify.api.model.chat.ChatMessage;
import com.songify.api.model.chat.ChatRoom;
import com.songify.api.model.chat.MessageStatus;
import com.songify.api.repository.ChatMessageRepository;
import com.songify.api.repository.ChatRoomRepository;
import com.songify.api.service.ChatMessageService;
import com.songify.api.service.ChatRoomService;
import com.songify.api.service.impl.ChatMessageServiceImpl;
import com.songify.api.service.impl.ChatRoomServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ChatMessageServiceTests {

    private ChatMessageRepository chatMessageRepository;
    private ChatMessageService chatMessageService;
    private ChatRoomRepository chatRoomRepository;
    private ChatRoomService chatRoomService;
    private List<ChatMessage> messages;

    @BeforeEach
    void createTestData(){
        this.chatMessageRepository = Mockito.mock(ChatMessageRepository.class);
        this.chatRoomRepository = Mockito.mock(ChatRoomRepository.class);
        this.chatRoomService = new ChatRoomServiceImpl(chatRoomRepository);
        this.chatMessageService = new ChatMessageServiceImpl(chatMessageRepository, chatRoomService);

        this.messages = new ArrayList<>();
        messages.add(new ChatMessage(0L, "CHATID", 56L, 34L, "sender name", "receiver name", "hello message", new Date(), MessageStatus.RECEIVED));
        messages.add(new ChatMessage(1L, "CHATID", 56L, 34L, "sender name", "receiver name", "oh, hello!", new Date(), MessageStatus.RECEIVED));
        messages.add(new ChatMessage(2L, "CHATID", 78L, 75L, "sender name", "receiver name", "saying hello.", new Date(), MessageStatus.RECEIVED));
    }

    @Test
    void findByMessageIdTest(){
        ChatMessage message = messages.get(2);
        Mockito.when(chatMessageRepository.findById(1L)).thenReturn(Optional.of(message));
        Mockito.when(chatMessageRepository.save(message)).thenReturn(message);

        ChatMessage actual = chatMessageService.findById(1L);

        Assertions.assertEquals("saying hello.", actual.getContent());
    }

    @Test
    void saveMessageTest(){
        ChatMessage message = new ChatMessage(10L, "chatid", 88L, 78L, "sender", "receiver", "content..", new Date(), MessageStatus.RECEIVED);
        Mockito.when(chatMessageRepository.save(message)).thenReturn(message);

        ChatMessage actual = chatMessageService.save(message);
        
        Assertions.assertEquals("content..", actual.getContent());
    }

    @Test
    void countNewMessagesTest(){
        List<ChatMessage> between56and34 = new ArrayList<>();
        for(ChatMessage i : messages){
            if(i.getSenderId() == 56L && i.getRecipientId() == 34L){
                between56and34.add(i);
            }
        }
        Mockito.when(chatMessageRepository.countBySenderIdAndRecipientIdAndStatus(56L, 34L, MessageStatus.RECEIVED)).thenReturn(between56and34.stream().count());

        Long count = chatMessageService.countNewMessages(56L, 34L);

        Assertions.assertEquals(2L, count);
    }

    @Test
    void findChatMessagesTest(){
        ChatRoom room = new ChatRoom(1L, "CHATID", 56L, 34L);
        Mockito.when(chatRoomRepository.findBySenderIdAndRecipientId(56L, 34L)).thenReturn(Optional.of(room));
        Mockito.when(chatMessageRepository.findByChatId("CHATID")).thenReturn(messages);

        List<ChatMessage> messagesFound = chatMessageService.findChatMessages(56L, 34L);

        Assertions.assertEquals(messages, messagesFound);
    }

    @Test
    void updateStatusesTest(){
        Mockito.when(chatMessageRepository.findAllBySenderIdAndRecipientId(56L, 34L)).thenReturn(messages);

        chatMessageService.updateStatuses(56L, 34L, MessageStatus.DELIVERED);

        Assertions.assertEquals(3, messages.size());
        Assertions.assertEquals(MessageStatus.DELIVERED, messages.get(0).getStatus());
        Assertions.assertEquals(MessageStatus.DELIVERED, messages.get(1).getStatus());
        Assertions.assertEquals(MessageStatus.DELIVERED, messages.get(2).getStatus());
    }
}
