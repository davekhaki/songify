package com.songify.api.config;

public class AuthenticationConstants {
    private AuthenticationConstants(){ throw new IllegalStateException("Utility Class"); }

    public static final String SECRET = "Songify_Secret";
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/v1/api/users/";
}
