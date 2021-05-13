package com.songify.api.model.dto;

import lombok.Data;

@Data
public class SpotifyGenre {
    private int id;
    private String name;
    private String href;
    private String iconUrl;
}
