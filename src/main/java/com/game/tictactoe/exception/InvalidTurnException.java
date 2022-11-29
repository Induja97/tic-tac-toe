package com.game.tictactoe.exception;

public class InvalidTurnException extends RuntimeException {

    public InvalidTurnException(String message) {
        super(message);
    }
}