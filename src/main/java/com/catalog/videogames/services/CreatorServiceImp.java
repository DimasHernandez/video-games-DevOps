package com.catalog.videogames.services;

import com.catalog.videogames.exceptions.CreatorNotFoundException;
import com.catalog.videogames.models.Creator;
import com.catalog.videogames.models.VideoGame;
import com.catalog.videogames.repositories.CreatorRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatorServiceImp implements CreatorService {

    private final CreatorRepository creatorRepository;

    public CreatorServiceImp(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    @Override
    public Creator save(Creator creator) {
        // Asegurar que el creator bien seteado y mantener la sincronizacion bidireccional
//        creator.getVideoGames().forEach(videoGame -> creator.addVideoGame(videoGame));
        creator.getVideoGames().forEach(creator::addVideoGame);
        return creatorRepository.save(creator);
    }

    @Override
    public Creator update(Long id, Creator creator) {

        Creator creatorDb = creatorRepository.findById(id)
                .orElseThrow(() -> new CreatorNotFoundException(String.format("Creator with id %d not found", id)));

        creatorDb.setName(creator.getName());
        creatorDb.setCountry(creator.getCountry());
        appendVideoGames(creator, creatorDb);

        return creatorRepository.save(creatorDb);
    }

    @Override
    public void delete(Long id) {

        Creator creatorDb = creatorRepository.findById(id)
                .orElseThrow(() -> new CreatorNotFoundException(String.format("Creator with id %d not found", id)));
//        creatorDb.getVideoGames().forEach(videoGame -> creatorDb.removeVideoGame(videoGame));
        creatorDb.getVideoGames().forEach(creatorDb::removeVideoGame);
        creatorRepository.delete(creatorDb);
    }

    private void appendVideoGames(Creator creator, Creator creatorDb) {

        // Preferir un codigo claro que un codigo moderno
        for (VideoGame videoGame : creator.getVideoGames()) {
            if (!creatorDb.getVideoGames().contains(videoGame)) {
                creatorDb.addVideoGame(videoGame);
            }
        }
//        creator.getVideoGames()
//                .stream()
//                .filter(videoGame -> !creatorDb.getVideoGames().contains(videoGame))
//                .forEach(creatorDb::addVideoGame);
    }
}
