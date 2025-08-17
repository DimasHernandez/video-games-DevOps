package com.catalog.videogames.repositories;

public interface VideoGameProjection {

    String getCreatorName();

    long getId();

    String getTitle();

    String getPlatform();
}
