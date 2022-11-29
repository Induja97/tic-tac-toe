package com.game.tictactoe.domain;

public enum Player {

    X('X'),
    O('O');
    private final Character value;

    Player(Character value) {
        this.value = value;
    }

    public Character getValue() {
        return value;
    }
}