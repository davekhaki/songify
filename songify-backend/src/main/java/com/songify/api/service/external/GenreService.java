package com.songify.api.service.external;

import com.songify.api.model.dto.SpotifyGenre;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GenreService {

    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);

    private final TokenService tokenService = new TokenService();

    static String url = "https://api.spotify.com/v1/browse/categories";

    RestTemplate restTemplate = new RestTemplate();

    public void exchangeRestTemplate(){
        tokenService.refreshToken();
        logger.info("----------------------------");
        logger.info(tokenService.getToken());
        logger.info("----------------------------");
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization" , "Bearer BQAFrAH-F57n2Zg8wmKFiBs-vOtovV2c6IvKIAAMMZNQ0QTpqEgXBUHR12Zg_ligdrKpeDujSolmvyio9zI");

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                requestEntity,
                String.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        logger.info("status code - " + statusCode);
        String user = responseEntity.getBody();
        logger.info("response body - " + user);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        logger.info("response Headers - " + responseHeaders);
    }


    public ResponseEntity<SpotifyGenre> getAllGenres() {
        exchangeRestTemplate();
        return null;
    }
}
