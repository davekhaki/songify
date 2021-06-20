package com.songify.api.servicetests;

import com.songify.api.service.SpotifyService;
import com.songify.api.service.impl.SpotifyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class SpotifyServiceTests {
    @Mock
    private RestTemplate restTemplate = new RestTemplate();

    @InjectMocks
    private SpotifyService spotifyService = new SpotifyServiceImpl();

    @Test
    void mockSpotifyApiResponseTest(){
        restTemplate.postForObject(Mockito.any(), Mockito.any(), Mockito.any());

        spotifyService.authenticate("code", new MockHttpServletResponse());

        Assertions.assertNotNull(this.restTemplate);
    }
}
