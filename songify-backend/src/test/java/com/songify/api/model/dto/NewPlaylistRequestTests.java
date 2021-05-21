package com.songify.api.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NewPlaylistRequestTests {

    @Test
    void getTitleTest(){
        NewPlaylistRequest request = new NewPlaylistRequest("title", "desc", "username");
        Assertions.assertEquals("title", request.getTitle());
    }

    @Test
    void setTitleTest(){
        NewPlaylistRequest request = new NewPlaylistRequest("title", "desc", "username");
        request.setTitle("new");
        Assertions.assertEquals("new", request.getTitle());
    }

    @Test
    void getDescTest(){
        NewPlaylistRequest request = new NewPlaylistRequest("title", "desc", "username");
        Assertions.assertEquals("desc", request.getDesc());
    }

    @Test
    void setDescTest(){
        NewPlaylistRequest request = new NewPlaylistRequest("title", "desc", "username");
        request.setDesc("new desc");
        Assertions.assertEquals("new desc", request.getDesc());
    }
}
