package com.catalog.videogames.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoGame> videoGames;

    public Creator() {
        this.videoGames = new ArrayList<>();
    }

    public Creator(String name, String country, List<VideoGame> videoGames) {
        this.name = name;
        this.country = country;
        this.videoGames = videoGames != null ? videoGames : new ArrayList<>();
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

    public List<VideoGame> getVideoGames() {
        return videoGames;
    }

    public void setVideoGames(List<VideoGame> videoGames) {
        this.videoGames = videoGames;
    }

    public void addVideoGame(VideoGame videoGame) {
        if (this.videoGames.contains(videoGame)) return;

        videoGame.setCreator(this);
        this.videoGames.add(videoGame);
    }

    public void removeVideoGame(VideoGame videoGame) {
        if (!this.videoGames.contains(videoGame)) return;

        this.videoGames.remove(videoGame);
        videoGame.setCreator(null);
    }
}
