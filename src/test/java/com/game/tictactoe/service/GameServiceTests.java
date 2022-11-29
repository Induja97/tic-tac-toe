package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.exception.InvalidTurnException;
import com.game.tictactoe.exception.PositionAlreadyOccupiedException;
import com.game.tictactoe.util.GameBoard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTests {

    private GameService gameService;

    @Before
    public void setUp() {

        GameBoard gameBoard = new GameBoard();
        gameService = new GameService(gameBoard);
    }

    @Test(expected = InvalidTurnException.class)
    public void shouldThrowInvalidTurnException() throws InvalidTurnException {

        assertThat(gameService.playGame(Player.O, 1)).isEqualTo("Player X should move first");
    }

    @Test
    public void savePositionOnBoard() {

        assertThat(gameService.playGame(Player.X, 1)).isEqualTo("Successful Move");
    }

    @Test
    public void playersShouldPlayAlternateTurns() {

        gameService.playGame(Player.X, 2);

        assertThat(gameService.playGame(Player.O, 1)).isEqualTo("Successful Move");
    }

    @Test(expected = InvalidTurnException.class)
    public void shouldThrowInvalidTurnExceptionWhenAlternatePlayerIsNotPlaying() {

        gameService.playGame(Player.X, 2);

        assertThat(gameService.playGame(Player.X, 1)).isEqualTo("Player O's turn now");
    }

    @Test(expected = PositionAlreadyOccupiedException.class)
    public void shouldThrowPositionAlreadyOccupiedException() {

        gameService.playGame(Player.X, 6);

        assertThat(gameService.playGame(Player.O, 6)).isEqualTo("Input position 5 is already occupied");
    }
}