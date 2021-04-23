package com.songify.api.exceptions;

public class JWTException extends RuntimeException{
    public JWTException(String message) { super("Something went wrong with JWT Authentication:" + message); }
}
