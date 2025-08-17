package com.catalog.videogames.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VideoGame> videoGames;

    public Creator() {
        this.videoGames = new HashSet<>();
    }

    public Creator(String name, String country, Set<VideoGame> videoGames) {
        this.name = name;
        this.country = country;
        this.videoGames = videoGames != null ? videoGames : new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<VideoGame> getVideoGames() {
        return videoGames;
    }

    public void setVideoGames(Set<VideoGame> videoGames) {
        this.videoGames = videoGames;
    }

    public void addVideoGame(VideoGame videoGame) {
        // Proteccion a videoGame duplicados. Se omite al trabajar con el Set<>
//        if (this.videoGames.contains(videoGame)) return;
        videoGame.setCreator(this);
        this.videoGames.add(videoGame);
    }

    public void removeVideoGame(VideoGame videoGame) {
        if (!this.videoGames.contains(videoGame)) return;

        this.videoGames.remove(videoGame);
        videoGame.setCreator(null);
    }
}
