package com.songify.api.config;

public class AuthenticationConstants {

    private static AuthenticationConstants test = null;
    private AuthenticationConstants(){ // Adding private constructor to hide implicit public constructor (SonarQube)
        throw new IllegalStateException("Utility class");
    }

    public static final String SECRET = "Java_to_Dev_Secret";
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/v1/api/users/add";

    public static AuthenticationConstants create(){
        if(test==null) test = new AuthenticationConstants();
        return test;
    }
}
