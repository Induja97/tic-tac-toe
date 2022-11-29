package com.game.tictactoe.service;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.exception.InvalidTurnException;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public String playGame(Player player) {

        String message = null;
        if (player == Player.X) {
            message = "Successful Move";
        } else if (player == Player.O) {
            message = "Player X should move first";
            throw new InvalidTurnException(message);
        }
        return message;
    }
}