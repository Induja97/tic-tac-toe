package com.game.tictactoe.service;

import com.game.tictactoe.exception.InvalidTurnException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTests {

    @Test(expected = InvalidTurnException.class)
    public void shouldThrowInvalidTurnException() throws InvalidTurnException {

        GameService gameService = new GameService();

        assertThat(gameService.playGame('O')).isEqualTo("Player X should move first");
    }
}