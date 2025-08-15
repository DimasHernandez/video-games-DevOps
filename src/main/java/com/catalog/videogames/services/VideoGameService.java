package com.catalog.videogames.services;

import com.catalog.videogames.models.Creator;
import com.catalog.videogames.models.VideoGame;

import java.util.List;

public interface VideoGameService {

    VideoGame save(VideoGame videoGame);

    VideoGame update(Long id, VideoGame videoGame);

    void delete(Long id);

    // TODO: Revisar si en lugar de recibir el parametro Creator, mejor recibir el id del Creator.
    List<VideoGame> listVideoGamesGroupedByCreator(Creator creator);
}
