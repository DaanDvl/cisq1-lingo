package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GameStatus;
import nl.hu.cisq1.lingo.trainer.presentation.dto.Guess;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This integration test integrates between the service layer,
 * the data layer and the framework.
 * In a dev environment, we test against the actual database.
 *
 * In continuous integration pipelines, we should not
 * use the actual database as we don't have one.
 * We want to replace it with an in-memory database.
 *
 * Set the profile to CI, so that application-ci.properties is loaded
 * and an import script is run.
 **/
@SpringBootTest
@ActiveProfiles("ci")
@Import(CiTestConfiguration.class)
class TrainerServiceIntegrationTest {

    @Autowired
    private TrainerService service;

    @Test
    @DisplayName("check if gamestatus gives same id back as in request")
    void providesGameStatus() throws Exception {
        List<Game> games = service.getAllGames();

        for (Game game : games) {
            GameStatus gameStatus = this.service.getStatus(game.getId());
            assertEquals(game.getId(), gameStatus.getId());
        }
    }

    @Test
    @DisplayName("guess the word right.")
    void guessWordRight() throws Exception {
        List<Game> games = service.getAllGames();

        for (Game game : games) {
            Guess guess = new Guess();
            guess.guess = game.getCurrentRound().getWordToGuess();

            GameStatus gameStatus = this.service.guess(game.getId(), guess);
            assertEquals(CORRECT, gameStatus.getLastFeedback().totalMark());
        }

    }
}
