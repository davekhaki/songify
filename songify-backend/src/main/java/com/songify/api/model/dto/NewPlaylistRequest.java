package com.songify.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewPlaylistRequest {
    private String title;
    private String desc;
}
