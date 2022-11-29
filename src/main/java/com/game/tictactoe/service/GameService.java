package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.exception.InvalidTurnException;
import com.game.tictactoe.util.GameBoard;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameBoard gameBoard;
    private char previousPlayer;

    public GameService(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public String playGame(Player player, int row, int column) {

        String message = "Successful Move";
        if (previousPlayer == 0 && isFirstPlayerO(player)) {
            message = "Player X should move first";
            throw new InvalidTurnException(message);
        }
        gameBoard.setPositionOfPlayerOnBoard(player, row, column);
        return message;
    }

    private boolean isFirstPlayerO(Player player) {
        return player == Player.O;
    }
}