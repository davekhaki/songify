package com.songify.api.service.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    private String token;

    private static final String URL = "https://accounts.spotify.com/api/token";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static String exchangeTokenRestTemplate(){
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization" , "Basic MzQ1ODFjOThkNmI4NGFhMGIxOWU0ODE3YjFkNmM5MDI= : YWQ0ZThjM2Y4MTY2NDM2ODhmN2ZkNWRmN2U5ZTQ3MGQ=");

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);


        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        logger.info("Status Code: {} .", statusCode);
        String token = responseEntity.getBody();
        logger.info("Response Body: {} .", token);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        logger.info("Response Headers: {} .",responseHeaders);

        return token;
    }

    public void refreshToken(){
        token = exchangeTokenRestTemplate();
    }

    public String getToken() {
        return token;
    }
}
