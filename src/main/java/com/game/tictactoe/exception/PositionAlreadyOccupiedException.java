package com.game.tictactoe.exception;

public class PositionAlreadyOccupiedException extends RuntimeException {

    public PositionAlreadyOccupiedException(String message) {
        super(message);
    }
}