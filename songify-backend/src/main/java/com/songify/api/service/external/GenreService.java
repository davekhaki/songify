package com.songify.api.service.external;

import com.songify.api.model.dto.SpotifyGenre;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenreService {

    private final static TokenService tokenService = new TokenService();

    static String url = "https://api.spotify.com/v1/browse/categories";

    static RestTemplate restTemplate = new RestTemplate();

    public static void exchangeRestTemplate(){
        tokenService.refreshToken();
        System.out.println("----------------------------");
        System.out.println(tokenService.getToken());
        System.out.println("----------------------------");
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization" , "Bearer BQAFrAH-F57n2Zg8wmKFiBs-vOtovV2c6IvKIAAMMZNQ0QTpqEgXBUHR12Zg_ligdrKpeDujSolmvyio9zI");

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                requestEntity,
                String.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("status code - " + statusCode);
        String user = responseEntity.getBody();
        System.out.println("response body - " + user);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println("response Headers - " + responseHeaders);
    }


    public ResponseEntity<SpotifyGenre> getAllGenres() {
        exchangeRestTemplate();
        return null;
    }
}
