package com.songify.api.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super("Could not find resource: " + message);
    }
}
