package com.catalog.videogames.services;

import com.catalog.videogames.models.Creator;
import com.catalog.videogames.models.VideoGame;
import com.catalog.videogames.repositories.VideoGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoGameServiceImpl implements VideoGameService {

    private final VideoGameRepository videoGameRepository;

    public VideoGameServiceImpl(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }


    @Override
    public VideoGame save(VideoGame videoGame) {
        return null;
    }

    @Override
    public VideoGame update(Long id, VideoGame videoGame) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    // TODO: Revisar la firma del metodo, si recibir mejor el objeto completo o solamente el id del Creator
    @Override
    public List<VideoGame> listVideoGamesGroupedByCreator(Creator creator) {
        return List.of();
    }
}
