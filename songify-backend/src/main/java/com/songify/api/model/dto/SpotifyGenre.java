package com.songify.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpotifyGenre {
    private int id;
    private String name;
    private String href;
    private String iconUrl;
}
