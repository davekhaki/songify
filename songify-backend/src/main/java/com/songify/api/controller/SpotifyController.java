package com.songify.api.controller;

import com.songify.api.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("v1/api/spotify")
@RequiredArgsConstructor
public class SpotifyController {

    private final SpotifyService spotifyService;

    @GetMapping("/authenticate")
    public void getToken(@RequestParam String code, HttpServletResponse httpServletResponse)
    {
        spotifyService.authenticate(code, httpServletResponse);
    }

    @GetMapping("/spotify-response")
    public void spotifyResponse(@RequestParam String code, HttpServletResponse httpServletResponse) {
        String _clientId = "34581c98d6b84aa0b19e4817b1d6c902";
        String _clientSecret = "ad4e8c3f816643688f7fd5df7e9e470d";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String usernamePassword = _clientId+":"+_clientSecret;
        String basicToken = Base64.getEncoder().encodeToString(usernamePassword.getBytes());
        httpHeaders.set("Authorization", "Basic "+basicToken);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("code", code.trim());
        requestBody.add("redirect_uri", "http://localhost:8080/v1/api/spotify/authenticate");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
        try {
            Object response = restTemplate.postForObject("https://accounts.spotify.com/api/token", httpEntity, Object.class);
            Map<String, Object> map = (Map<String, Object>) response;
            map.forEach((k, v) -> System.out.println(k+": "+v));
            httpServletResponse.setStatus(302);
            String accessToken = (String) map.get("access_token");
            String patientId = (String) map.get("patient");
            String url = "http://localhost:3000/success-spotify/"+accessToken;
            httpServletResponse.setHeader("Location", url); // redirect to success page
        }
        catch(HttpClientErrorException e) {
            e.printStackTrace();
        }
    }
}
