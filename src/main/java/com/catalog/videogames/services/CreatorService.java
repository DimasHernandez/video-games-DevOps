package com.catalog.videogames.services;

import com.catalog.videogames.models.Creator;

public interface CreatorService {

    Creator save(Creator creator);

    Creator update(Long id, Creator creator);

    void delete(Long id);
}
