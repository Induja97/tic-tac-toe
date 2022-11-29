package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.domain.Position;
import com.game.tictactoe.exception.InvalidPositionException;
import com.game.tictactoe.exception.InvalidTurnException;
import com.game.tictactoe.exception.PositionAlreadyOccupiedException;
import com.game.tictactoe.util.GameBoard;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static final int INITIAL_VALUE = 0;
    private final GameBoard gameBoard;
    private char previousPlayer;

    public GameService(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public String playGame(Player player, int position) {

        validateCurrentTurn(player, position);
        saveCurrentTurn(player, position);
        return validateGameAndSendResponse(player);
    }

    private String validateGameAndSendResponse(Player player) {

        if (gameBoard.isBoardFull()) {
            return "Game is a Tie";
        } else if (isGameOver()) {
            return String.format("Player %s won the game", player.getValue());
        }
        return "Successful Move";
    }

    private boolean isGameOver() {
        return gameBoard.isAnyRowOccupiedBySamePlayer() || gameBoard.isAnyColumnOccupiedBySamePlayer()
                || gameBoard.isAnyDiagonalOccupiedBySamePlayer();
    }

    private void saveCurrentTurn(Player player, int position) {

        previousPlayer = player.getValue();
        gameBoard.setPositionOfPlayerOnBoard(player, Position.getRowColumnValueOfPosition(position));
    }

    private void validateCurrentTurn(Player player, int position) {

        if (Position.getRowColumnValueOfPosition(position) == Position.DEFAULT) {
            throw new InvalidPositionException(String.format("Input %s position is invalid", position));
        } else if (isFirstTurn() && isPlayerO(player)) {
            throw new InvalidTurnException("Player X should move first");
        } else if (isNotAlternatePlayerPlaying(player)) {
            throw new InvalidTurnException(String.format("Player %s's turn now", getNextPlayer(player)));
        } else if (isPositionOccupied(position)) {
            throw new PositionAlreadyOccupiedException(String.format("Input position %s is already occupied", position));
        }
    }

    private Character getNextPlayer(Player player) {
        return player == Player.X ? Player.O.getValue() : Player.X.getValue();
    }

    private boolean isPositionOccupied(int position) {
        return gameBoard.getPositionValueOnBoard(Position.getRowColumnValueOfPosition(position)) != INITIAL_VALUE;
    }

    private boolean isNotAlternatePlayerPlaying(Player player) {
        return player.getValue() == previousPlayer;
    }

    private boolean isFirstTurn() {
        return previousPlayer == INITIAL_VALUE;
    }

    private boolean isPlayerO(Player player) {
        return player == Player.O;
    }
}