package com.catalog.videogames.services;

import com.catalog.videogames.models.Creator;
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
        return null;
    }

    @Override
    public Creator update(Long id, Creator creator) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
