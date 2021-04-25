package com.songify.api.controller;

import com.songify.api.model.dto.SpotifyGenre;
import com.songify.api.service.external.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/api/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("")
    public ResponseEntity<SpotifyGenre> getGenres(){
        return this.genreService.getAllGenres();
    }

}
