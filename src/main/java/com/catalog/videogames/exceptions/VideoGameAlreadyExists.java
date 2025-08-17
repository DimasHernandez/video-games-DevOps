package com.catalog.videogames.exceptions;

public class VideoGameAlreadyExists extends RuntimeException {

    public VideoGameAlreadyExists(String message) {
        super(message);
    }
}
