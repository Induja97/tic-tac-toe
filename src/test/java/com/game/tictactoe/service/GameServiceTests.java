package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.exception.InvalidTurnException;
import com.game.tictactoe.util.GameBoard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTests {

    @Test(expected = InvalidTurnException.class)
    public void shouldThrowInvalidTurnException() throws InvalidTurnException {

        GameBoard gameBoard = new GameBoard();
        GameService gameService = new GameService(gameBoard);

        assertThat(gameService.playGame(Player.O, 1)).isEqualTo("Player X should move first");
    }

    @Test
    public void savePositionOnBoard() {

        GameBoard gameBoard = new GameBoard();
        GameService gameService = new GameService(gameBoard);

        assertThat(gameService.playGame(Player.X, 1)).isEqualTo("Successful Move");
    }

    @Test
    public void playersShouldPlayAlternateTurns() {

        GameBoard gameBoard = new GameBoard();
        GameService gameService = new GameService(gameBoard);
        gameService.playGame(Player.X, 2);

        assertThat(gameService.playGame(Player.O, 1)).isEqualTo("Successful Move");
    }
}