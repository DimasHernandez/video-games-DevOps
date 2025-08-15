package com.catalog.videogames.repositories;

import com.catalog.videogames.models.Creator;
import org.springframework.data.repository.CrudRepository;

public interface CreatorRepository extends CrudRepository<Creator, Long> {
}
