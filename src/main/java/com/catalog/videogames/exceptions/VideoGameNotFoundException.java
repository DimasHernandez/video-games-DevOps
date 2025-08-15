package com.catalog.videogames.exceptions;

public class VideoGameNotFoundException extends RuntimeException {

    public VideoGameNotFoundException(String message) {
        super(message);
    }
}
