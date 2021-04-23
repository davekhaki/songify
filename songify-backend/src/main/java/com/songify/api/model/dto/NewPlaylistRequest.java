package com.songify.api.model.dto;

import lombok.Data;

@Data
public class NewPlaylistRequest {
    private String title;
    private String desc;
}
