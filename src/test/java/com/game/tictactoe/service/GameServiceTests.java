package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.exception.InvalidPositionException;
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

        gameService.playGame(Player.O, 1);
    }

    @Test
    public void savePositionOnBoard() {

        assertThat(gameService.playGame(Player.X, 1).getStatus()).isEqualTo("GAME_IN_PROGRESS");
    }

    @Test
    public void playersShouldPlayAlternateTurns() {

        gameService.playGame(Player.X, 2);

        assertThat(gameService.playGame(Player.O, 1).getStatus()).isEqualTo("GAME_IN_PROGRESS");
    }

    @Test(expected = InvalidTurnException.class)
    public void shouldThrowInvalidTurnExceptionWhenAlternatePlayerIsNotPlaying() {

        gameService.playGame(Player.X, 2);
        gameService.playGame(Player.X, 1);
    }

    @Test(expected = PositionAlreadyOccupiedException.class)
    public void shouldThrowPositionAlreadyOccupiedException() {

        gameService.playGame(Player.X, 6);
        gameService.playGame(Player.O, 6);
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInFirstRowAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 1);
        gameService.playGame(Player.O, 4);
        gameService.playGame(Player.X, 2);
        gameService.playGame(Player.O, 5);

        assertThat(gameService.playGame(Player.X, 3).getResult()).isEqualTo("Player X won the game");
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInSecondRowAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 1);
        gameService.playGame(Player.O, 4);
        gameService.playGame(Player.X, 2);
        gameService.playGame(Player.O, 5);
        gameService.playGame(Player.X, 7);

        assertThat(gameService.playGame(Player.O, 6).getResult()).isEqualTo("Player O won the game");
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInThirdRowAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 7);
        gameService.playGame(Player.O, 4);
        gameService.playGame(Player.X, 8);
        gameService.playGame(Player.O, 5);

        assertThat(gameService.playGame(Player.X, 9).getResult()).isEqualTo("Player X won the game");
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInFirstColumnAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 1);
        gameService.playGame(Player.O, 3);
        gameService.playGame(Player.X, 4);
        gameService.playGame(Player.O, 5);

        assertThat(gameService.playGame(Player.X, 7).getResult()).isEqualTo("Player X won the game");
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInSecondColumnAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 2);
        gameService.playGame(Player.O, 4);
        gameService.playGame(Player.X, 5);
        gameService.playGame(Player.O, 1);

        assertThat(gameService.playGame(Player.X, 8).getResult()).isEqualTo("Player X won the game");
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInThirdColumnAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 2);
        gameService.playGame(Player.O, 3);
        gameService.playGame(Player.X, 5);
        gameService.playGame(Player.O, 6);
        gameService.playGame(Player.X, 1);

        assertThat(gameService.playGame(Player.O, 9).getResult()).isEqualTo("Player O won the game");
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInFirstDiagonalAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 1);
        gameService.playGame(Player.O, 3);
        gameService.playGame(Player.X, 5);
        gameService.playGame(Player.O, 6);

        assertThat(gameService.playGame(Player.X, 9).getResult()).isEqualTo("Player X won the game");
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInSecondDiagonalAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 1);
        gameService.playGame(Player.O, 3);
        gameService.playGame(Player.X, 6);
        gameService.playGame(Player.O, 5);
        gameService.playGame(Player.X, 2);

        assertThat(gameService.playGame(Player.O, 7).getResult()).isEqualTo("Player O won the game");
    }

    @Test
    public void shouldDeclareGameAsTieIfAllPositionsAreFilled() {

        gameService.playGame(Player.X, 1);
        gameService.playGame(Player.O, 3);
        gameService.playGame(Player.X, 2);
        gameService.playGame(Player.O, 5);
        gameService.playGame(Player.X, 7);
        gameService.playGame(Player.O, 8);
        gameService.playGame(Player.X, 9);
        gameService.playGame(Player.O, 4);

        assertThat(gameService.playGame(Player.X, 6).getResult()).isEqualTo("Game is a Tie");
    }

    @Test(expected = InvalidPositionException.class)
    public void shouldThrowInvalidPositionException() {

        gameService.playGame(Player.X, 0);
    }

    @Test
    public void gameShouldResetOnceWinnerIsIdentified() {

        gameService.playGame(Player.X, 1);
        gameService.playGame(Player.O, 4);
        gameService.playGame(Player.X, 2);
        gameService.playGame(Player.O, 5);

        assertThat(gameService.playGame(Player.X, 3).getResult()).isEqualTo("Player X won the game");
        assertThat(gameService.playGame(Player.X, 3).getStatus()).isEqualTo("GAME_IN_PROGRESS");
    }

    @Test
    public void gameShouldResetIfPreviousGameIsATie() {

        gameService.playGame(Player.X, 1);
        gameService.playGame(Player.O, 3);
        gameService.playGame(Player.X, 2);
        gameService.playGame(Player.O, 5);
        gameService.playGame(Player.X, 7);
        gameService.playGame(Player.O, 8);
        gameService.playGame(Player.X, 9);
        gameService.playGame(Player.O, 4);

        assertThat(gameService.playGame(Player.X, 6).getResult()).isEqualTo("Game is a Tie");
        assertThat(gameService.playGame(Player.X, 3).getStatus()).isEqualTo("GAME_IN_PROGRESS");
    }
}