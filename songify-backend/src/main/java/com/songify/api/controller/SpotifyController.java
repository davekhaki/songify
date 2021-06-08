package com.songify.api.controller;

import com.songify.api.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

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
}
