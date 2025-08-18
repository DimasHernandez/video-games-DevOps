package com.catalog.videogames.utils;

import com.catalog.videogames.models.Creator;
import com.catalog.videogames.models.Platform;
import com.catalog.videogames.models.VideoGame;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DataMock {

    public static Creator creatorMock() {
        return new Creator(1L, "Nintendo S.A", "Japan", videoGamesMock());
    }

    public static Set<VideoGame> videoGamesMock() {
        return new HashSet<>(Arrays.asList(
                new VideoGame(1L, "Zelda", Platform.NINTENDO_SWITCH, LocalDate.now().minusDays(1)),
                new VideoGame(2L, "Mario", Platform.NINTENDO_SWITCH, LocalDate.now().plusDays(7)),
                new VideoGame(3L, "FIFA 25", Platform.PLAY_STATION, LocalDate.now()),
                new VideoGame(4L, "LOL", Platform.PC, LocalDate.now().minusDays(4)),
                new VideoGame(5L, "World of Warcraft", Platform.PC, LocalDate.now().plusDays(3)),
                new VideoGame(6L, "Zombie state", Platform.ANDROID, LocalDate.now().plusDays(2)),
                new VideoGame(7L, "Clash Royal", Platform.IOS, LocalDate.now().plusDays(1)),
                new VideoGame(8L, "Fallout 4", Platform.XBOX, LocalDate.now().plusDays(30)))
        );
    }
}
