package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.exception.InvalidTurnException;
import com.game.tictactoe.util.GameBoard;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameBoard gameBoard;

    public GameService(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public String playGame(Player player, int row, int column) {

        String message = null;
        if (player == Player.X) {
            message = "Successful Move";
        } else if (player == Player.O) {
            message = "Player X should move first";
            throw new InvalidTurnException(message);
        }
        gameBoard.setPositionOfPlayerOnBoard(player, row, column);
        return message;
    }
}