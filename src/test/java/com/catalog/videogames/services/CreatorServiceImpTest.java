package com.catalog.videogames.services;

import com.catalog.videogames.exceptions.CreatorNotFoundException;
import com.catalog.videogames.models.Creator;
import com.catalog.videogames.models.Platform;
import com.catalog.videogames.models.VideoGame;
import com.catalog.videogames.repositories.CreatorRepository;
import com.catalog.videogames.utils.DataMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatorServiceImpTest {

    @Mock
    private CreatorRepository creatorRepository;

    @InjectMocks
    private CreatorServiceImp creatorService;

    // Argument Captor
    @Captor
    private ArgumentCaptor<Creator> creatorArgumentCaptor;


    @Test
    void shouldSaveCreatorSuccessfullyTest() {
        // Arrange
        Creator creator = DataMock.creatorMock();
        when(creatorRepository.save(any(Creator.class)))
                .thenReturn(DataMock.creatorMock());

        // Act
        Creator creatorActual = creatorService.save(creator);

        // Assert
        assertNotNull(creatorActual);
        assertEquals(creator, creatorActual);
        assertEquals(creator.getName(), creatorActual.getName());
        assertEquals(creator.getVideoGames().size(), creatorActual.getVideoGames().size());
    }

    @Test
    void shouldThrowExceptionWhenCreatorIsNull() {
        // Arrange
        String message = "Illegal argument, please review the data sent";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> creatorService.save(null));

        // Assert
        assertEquals(IllegalArgumentException.class, exception.getClass());
        assertEquals(message, exception.getMessage());
    }

    @Test
    void shouldUpdateCreatorSuccessfullyTest() {
        // Arrange
        Long id = 1L;
        Set<VideoGame> videoGames = new HashSet<>(Arrays.asList(
                new VideoGame(9L, "Sonic", Platform.NINTENDO_SWITCH, LocalDate.now()),
                new VideoGame(10L, "Taxi Driver", Platform.ANDROID, LocalDate.now()),
                new VideoGame(11L, "Fornite", Platform.PLAY_STATION, LocalDate.now()))
        );
        Creator creatorToUpdate = new Creator(1L, "Nintendo X.Y.Z", "Japan", videoGames);

        Creator expectedCreator = new Creator(
                1L,
                creatorToUpdate.getName(),
                creatorToUpdate.getCountry(),
                new HashSet<>() {{
                    addAll(DataMock.videoGamesMock());
                    addAll(creatorToUpdate.getVideoGames());
                }}
        );

        when(creatorRepository.findById(id))
                .thenReturn(Optional.of(DataMock.creatorMock()));
        when(creatorRepository.save(any(Creator.class)))
                .thenReturn(expectedCreator);
        // Act
        Creator creatorActual = creatorService.update(id, creatorToUpdate);

        // Assert
        assertNotNull(creatorActual);
        assertEquals(creatorToUpdate.getName(), creatorActual.getName());
        assertEquals(expectedCreator.getVideoGames().size(), creatorActual.getVideoGames().size());
        // Confirmar que los nuevos video jeugos fueron agregados
        assertTrue(creatorActual.getVideoGames().containsAll(expectedCreator.getVideoGames()));
    }

    @Test
    void shouldUpdateCreatorSuccessfullyWithArgumentCaptorTest() {
        // Arrange
        Long id = 1L;
        Set<VideoGame> videoGames = new HashSet<>(Arrays.asList(
                new VideoGame(9L, "Sonic", Platform.NINTENDO_SWITCH, LocalDate.now()),
                new VideoGame(10L, "Taxi Driver", Platform.ANDROID, LocalDate.now()),
                new VideoGame(11L, "Fornite", Platform.PLAY_STATION, LocalDate.now()))
        );
        Creator creatorToUpdate = new Creator(1L, "Nintendo X.Y.Z", "Japan", videoGames);

        Creator expectedCreator = new Creator(
                1L,
                creatorToUpdate.getName(),
                creatorToUpdate.getCountry(),
                new HashSet<>() {{
                    addAll(DataMock.videoGamesMock());
                    addAll(creatorToUpdate.getVideoGames());
                }}
        );

        when(creatorRepository.findById(id))
                .thenReturn(Optional.of(DataMock.creatorMock()));

        // Act
        creatorService.update(id, creatorToUpdate);

        // Estamos capturando el objeto creatorDb antes de guardar en la base de datos
        verify(creatorRepository).save(creatorArgumentCaptor.capture());
        Creator captured = creatorArgumentCaptor.getValue();

        // Assert
        assertEquals(expectedCreator.getName(), captured.getName());
        assertEquals(expectedCreator.getVideoGames().size(), captured.getVideoGames().size());
        // Confirmar que los nuevos video jeugos fueron agregados
        assertTrue(expectedCreator.getVideoGames().containsAll(captured.getVideoGames()));
        // Validar que los juegos capturados tengan un creador seteado correctamente
        captured.getVideoGames().stream()
                .filter(videoGames::contains)
                .forEach(videoGame -> assertEquals(captured, videoGame.getCreator()));
    }

    @Test
    void shouldThrowExceptionWhenNotExistsCreatorInUpdateTest() {
        // Arrange
        Long id = 2L;
        Creator creatorToUpdate = new Creator();
        String message = String.format("Creator with id %d not found", id);

        // Act
        Exception exception = assertThrows(CreatorNotFoundException.class,
                () -> creatorService.update(id, creatorToUpdate));

        // Assert
        assertEquals(CreatorNotFoundException.class, exception.getClass());
        assertEquals(message, exception.getMessage());
    }

    @Test
    void shouldDeleteCreatorSuccessfullyTest() {
        // Arrange
        Long id = 1L;
        Creator creatorToDelete = DataMock.creatorMock();
        when(creatorRepository.findById(id)).thenReturn(Optional.of(creatorToDelete));
        // Act
        creatorService.delete(id);

        // Assert
        verify(creatorRepository, times(1)).delete(creatorToDelete);
        assertEquals(0, creatorToDelete.getVideoGames().size());
    }

    @Test
    void shouldDeleteCreatorSuccessfullyWithArgumentCaptorTest() {
        // Arrange
        Long id = 1L;
        Creator creatorToDelete = DataMock.creatorMock();
        when(creatorRepository.findById(id)).thenReturn(Optional.of(creatorToDelete));
        // Act
        creatorService.delete(id);

        verify(creatorRepository).delete(creatorArgumentCaptor.capture());
        Creator captured = creatorArgumentCaptor.getValue();

        // Assert
        assertEquals(0, captured.getVideoGames().size());
    }

    @Test
    void shouldThrowExceptionWhenNotExistsCreatorInDeleteTest() {
        // Arrange
        Long id = 1L;
        String message = String.format("Creator with id %d not found", id);

        // Act
        Exception exception = assertThrows(CreatorNotFoundException.class,
                () -> creatorService.delete(id));

        // Assert
        assertEquals(CreatorNotFoundException.class, exception.getClass());
        assertEquals(message, exception.getMessage());
    }


}