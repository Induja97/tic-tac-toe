package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.domain.Position;
import com.game.tictactoe.exception.InvalidTurnException;
import com.game.tictactoe.exception.PositionAlreadyOccupiedException;
import com.game.tictactoe.util.GameBoard;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static final int INITIAL_PLAYER_VALUE = 0;
    private final GameBoard gameBoard;
    private char previousPlayer;

    public GameService(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public String playGame(Player player, int position) {

        String message = "Successful Move";
        validateCurrentTurn(player, position);
        previousPlayer = player.getValue();
        gameBoard.setPositionOfPlayerOnBoard(player, Position.getRowColumnValueOfPosition(position));
        return message;
    }

    private void validateCurrentTurn(Player player, int position) {

        if (isFirstTurn() && isPlayerO(player)) {
            throw new InvalidTurnException("Player X should move first");
        } else if (isNotAlternatePlayerPlaying(player)) {
            throw new InvalidTurnException(String.format("Player %s's turn now", player.getValue()));
        } else if (gameBoard.getPositionValueOnBoard(Position.getRowColumnValueOfPosition(position)) != 0) {
            throw new PositionAlreadyOccupiedException(String.format("Input position %s is already occupied", position));
        }
    }

    private boolean isNotAlternatePlayerPlaying(Player player) {
        return player.getValue() == previousPlayer;
    }

    private boolean isFirstTurn() {
        return previousPlayer == INITIAL_PLAYER_VALUE;
    }

    private boolean isPlayerO(Player player) {
        return player == Player.O;
    }
}