package com.songify.api.service.external;

import com.songify.api.model.dto.SpotifyGenre;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class GenreService {

    final String url = "https://api.spotify.com/v1/browse/categories";
    URI setUri(String url){
        try{
            URI uri = new URI(url);
            return uri;
        } catch (URISyntaxException e){
            e.printStackTrace();
        }
        return null;
    }

    final HttpHeaders headers = new HttpHeaders();
    //headers.set("Authorization", "Bearer BQDRWQ7rNsJgHushi1qWCKhF-e0nH05zj9XAMaXseeGjYb4_VSeZWyJKSHiqNAWOQ1iZa8429Kfe3udNYi4");



    HttpEntity<SpotifyGenre> entity = new HttpEntity<>(null, headers);

    public ResponseEntity<SpotifyGenre> getAllGenres() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SpotifyGenre> genres = restTemplate.exchange(setUri(url), HttpMethod.GET, entity, SpotifyGenre.class );
        return genres;
    }
}
