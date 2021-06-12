package com.songify.api.servicetests;

import com.songify.api.service.ChatRoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class ChatRoomServiceTests {

    @Autowired
    private ChatRoomService chatRoomService;

    @Test
    void getChatIdTest(){
        Optional<String> chatId = chatRoomService.getChatId(998L, 999L, true);
        Assertions.assertEquals("998_999", chatId.get());
    }
}
