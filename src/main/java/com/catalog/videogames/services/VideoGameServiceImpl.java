package com.catalog.videogames.services;

import com.catalog.videogames.exceptions.CreatorNotFoundException;
import com.catalog.videogames.exceptions.VideoGameAlreadyExists;
import com.catalog.videogames.exceptions.VideoGameNotFoundException;
import com.catalog.videogames.models.Creator;
import com.catalog.videogames.models.VideoGame;
import com.catalog.videogames.repositories.CreatorRepository;
import com.catalog.videogames.repositories.VideoGameProjection;
import com.catalog.videogames.repositories.VideoGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VideoGameServiceImpl implements VideoGameService {

    private final VideoGameRepository videoGameRepository;

    private final CreatorRepository creatorRepository;

    public VideoGameServiceImpl(VideoGameRepository videoGameRepository, CreatorRepository creatorRepository) {
        this.videoGameRepository = videoGameRepository;
        this.creatorRepository = creatorRepository;
    }

    @Override
    public VideoGame save(VideoGame videoGame) {

        Creator creator = creatorRepository.findById(videoGame.getCreator().getId())
                .orElseThrow(() -> new CreatorNotFoundException(String.format("Creator with id %s not found",
                        videoGame.getCreator().getId())));

        boolean existsVideoGame = videoGameRepository.existsByTitleAndPlatform(videoGame.getTitle(), videoGame.getPlatform());

        if (existsVideoGame)
            throw new VideoGameAlreadyExists(String.format("video game with title %s and platform %s already exists",
                    videoGame.getTitle(), videoGame.getPlatform()));

        creator.addVideoGame(videoGame);
        videoGame.setCreator(creator);
        return videoGameRepository.save(videoGame);
    }

    @Override
    public VideoGame update(Long id, VideoGame videoGame) {

        VideoGame videoGameDb = videoGameRepository.findById(id)
                .orElseThrow(() -> new VideoGameNotFoundException(String.format("video game with id %d not found", id)));

        Creator newCreator = creatorRepository.findById(videoGame.getCreator().getId())
                .orElseThrow(() -> new CreatorNotFoundException(String.format("Creator with id %d not found",
                        videoGame.getCreator().getId())));

        videoGameDb.setTitle(videoGame.getTitle());
        videoGameDb.setPlatform(videoGame.getPlatform());
        videoGameDb.setReleaseDate(videoGame.getReleaseDate());

        Creator oldCreator = videoGameDb.getCreator();
        if (oldCreator.getId().equals(newCreator.getId()))
            return videoGameRepository.save(videoGameDb);

        // Remover el juego del creador anterior
        oldCreator.removeVideoGame(videoGameDb);
        // Agregarlo al nuevo creador
        newCreator.addVideoGame(videoGameDb);
        videoGameDb.setCreator(newCreator);

        return videoGameRepository.save(videoGameDb);
    }

    @Override
    public VideoGame findById(Long id) {
        return videoGameRepository.findById(id)
                .orElseThrow(() -> new VideoGameNotFoundException(String.format("video game with id %d not found", id)));
    }

    @Override
    public void delete(Long id) {
        VideoGame videoGame = videoGameRepository.findById(id)
                .orElseThrow(() -> new VideoGameNotFoundException(String.format("video game with id %d not found", id)));

        Creator creator = videoGame.getCreator();
        creator.removeVideoGame(videoGame);
        videoGameRepository.delete(videoGame);
    }

    @Override
    public Set<VideoGame> listVideoGamesByCreator(Long creatorId) {
        Creator creator = creatorRepository.findById(creatorId)
                .orElseThrow(() -> new CreatorNotFoundException(String.format("Creator with id %d not found", creatorId)));
        return creator.getVideoGames();
    }

    @Override
    public Map<String, List<VideoGameProjection>> listAllVideoGamesGroupedByCreator() {

        List<VideoGameProjection> videoGameProjections = videoGameRepository.findAllGroupByCreator();
        return videoGameProjections.stream()
                .collect(Collectors.groupingBy(VideoGameProjection::getCreatorName));
    }
}
