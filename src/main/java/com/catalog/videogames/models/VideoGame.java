package com.catalog.videogames.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class VideoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Creator creator;

    public VideoGame() {
    }

    public VideoGame(String title, Platform platform, LocalDate releaseDate) {
        this.title = title;
        this.platform = platform;
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.isNull(obj)) return false;

        if (!(obj instanceof VideoGame)) return false;

        return this.id.equals(((VideoGame) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }
}
