package com.songify.api.model.dto;

public class SpotifyGenre {
    private int id;
    private String name;
    private String href;
    private String iconUrl;

    public SpotifyGenre(int id, String name, String href, String iconUrl) {
        this.id = id;
        this.name = name;
        this.href = href;
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
