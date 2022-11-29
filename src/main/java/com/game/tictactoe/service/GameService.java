package com.game.tictactoe.service;

import com.game.tictactoe.exception.InvalidTurnException;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public String playGame(char player) {

        String message = null;
        if (player == 'X') {
            message = "Successful Move";
        } else if (player == 'O') {
            message = "Player X should move first";
            throw new InvalidTurnException(message);
        }
        return message;
    }
}