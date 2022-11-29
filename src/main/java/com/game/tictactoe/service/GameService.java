package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.domain.Position;
import com.game.tictactoe.exception.InvalidTurnException;
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
        if (isFirstTurn() && isPlayerO(player)) {
            message = "Player X should move first";
            throw new InvalidTurnException(message);
        }
        if (player.getValue() == previousPlayer) {
            message = String.format("Player %s's turn now", player.getValue());
        } else {
            previousPlayer = player.getValue();
            gameBoard.setPositionOfPlayerOnBoard(player, Position.getRowColumnValueOfPosition(position));
        }
        return message;
    }

    private boolean isFirstTurn() {
        return previousPlayer == INITIAL_PLAYER_VALUE;
    }

    private boolean isPlayerO(Player player) {
        return player == Player.O;
    }
}