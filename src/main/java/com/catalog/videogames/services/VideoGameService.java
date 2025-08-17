package com.catalog.videogames.services;

import com.catalog.videogames.models.VideoGame;
import com.catalog.videogames.repositories.VideoGameProjection;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface VideoGameService {

    VideoGame save(VideoGame videoGame);

    VideoGame update(Long id, VideoGame videoGame);

    VideoGame findById(Long id);

    void delete(Long id);

    Set<VideoGame> listVideoGamesByCreator(Long creatorId);

    Map<String, List<VideoGameProjection>> listAllVideoGamesGroupedByCreator();
}
