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

    @Test
    public void shouldDeclareWinnerIfAllPositionsInFirstRowAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 1);
        gameService.playGame(Player.O, 4);
        gameService.playGame(Player.X, 2);
        gameService.playGame(Player.O, 5);

        assertThat(gameService.playGame(Player.X, 3)).isEqualTo("Player X won the game");
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInSecondRowAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 1);
        gameService.playGame(Player.O, 4);
        gameService.playGame(Player.X, 2);
        gameService.playGame(Player.O, 5);
        gameService.playGame(Player.X, 7);

        assertThat(gameService.playGame(Player.O, 6)).isEqualTo("Player O won the game");
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInThirdRowAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 7);
        gameService.playGame(Player.O, 4);
        gameService.playGame(Player.X, 8);
        gameService.playGame(Player.O, 5);

        assertThat(gameService.playGame(Player.X, 9)).isEqualTo("Player X won the game");
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInFirstColumnAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 1);
        gameService.playGame(Player.O, 3);
        gameService.playGame(Player.X, 4);
        gameService.playGame(Player.O, 5);

        assertThat(gameService.playGame(Player.X, 7)).isEqualTo("Player X won the game");
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInSecondColumnAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 2);
        gameService.playGame(Player.O, 4);
        gameService.playGame(Player.X, 5);
        gameService.playGame(Player.O, 1);

        assertThat(gameService.playGame(Player.X, 8)).isEqualTo("Player X won the game");
    }

    @Test
    public void shouldDeclareWinnerIfAllPositionsInThirdColumnAreFilledBySamePlayer() {

        gameService.playGame(Player.X, 2);
        gameService.playGame(Player.O, 3);
        gameService.playGame(Player.X, 5);
        gameService.playGame(Player.O, 6);
        gameService.playGame(Player.X, 1);

        assertThat(gameService.playGame(Player.O, 9)).isEqualTo("Player O won the game");
    }
}