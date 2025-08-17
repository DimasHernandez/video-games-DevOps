package com.catalog.videogames.repositories;

import com.catalog.videogames.models.Platform;
import com.catalog.videogames.models.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {

    boolean existsByTitleAndPlatform(String title, Platform platform);

    @Query(value = "select c.name as creator_name, vg.id, vg.title, vg.platform from Creator c JOIN VideoGame vg ON c.id = vg.creator.id order by c.name", nativeQuery = true)
    List<VideoGameProjection> findAllGroupByCreator();
}
