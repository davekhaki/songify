package com.songify.api.servicetests;

import com.songify.api.model.chat.ChatRoom;
import com.songify.api.repository.ChatRoomRepository;
import com.songify.api.service.ChatRoomService;
import com.songify.api.service.impl.ChatRoomServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
class ChatRoomServiceTests {

    @Autowired
    private ChatRoomService chatRoomService;
    private ChatRoomRepository chatRoomRepository;
    private ChatRoom room;

    @BeforeEach
    void createTestData(){
        this.chatRoomRepository = Mockito.mock(ChatRoomRepository.class);
        this.chatRoomService = new ChatRoomServiceImpl(chatRoomRepository);

        this.room = new ChatRoom(1L, "12_15",12L, 15L);
    }

    @Test
    void getExistingChatIdTest(){
        Mockito.when(chatRoomRepository.findBySenderIdAndRecipientId(12L, 15L)).thenReturn(Optional.of(room));
        Optional<String> chatId = chatRoomService.getChatId(12L, 15L, true);
        Assertions.assertEquals("12_15", chatId.get());
    }

    @Test
    void createNewIfNotExistFalseReturnsOptionalEmptyTest(){
        Mockito.when(chatRoomRepository.findBySenderIdAndRecipientId(67L, 66L)).thenReturn(Optional.empty());
        Optional<String> chatId = chatRoomService.getChatId(67L, 66L, false);
        Assertions.assertEquals(Optional.empty(), chatId);
    }

    @Test
    void getNewChatIdTest(){
        ChatRoom eightyEight = new ChatRoom(2L, "88_89", 88L, 89L);
        ChatRoom eightyNine = new ChatRoom(3L, "89_88", 89L, 88L);
        Mockito.when(chatRoomRepository.findBySenderIdAndRecipientId(88L, 89L)).thenReturn(Optional.empty());
        Mockito.when(chatRoomRepository.save(new ChatRoom(2L, "88_89", 88L, 89L))).thenReturn(eightyEight);
        Mockito.when(chatRoomRepository.save(new ChatRoom(3L, "89_88", 89L, 88L))).thenReturn(eightyNine);

        Optional<String> chatId = chatRoomService.getChatId(88L, 89L, true);

        Assertions.assertEquals("88_89", chatId.get());
    }
}
