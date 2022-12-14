package com.game.tictactoe.util;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.domain.Position;
import org.springframework.stereotype.Component;

import java.nio.CharBuffer;
import java.util.Arrays;

@Component
public class GameBoard {

    public static final int TOTAL_POSITIONS_ON_BOARD = 9;
    private static final int EMPTY_POSITION_ON_BOARD = 0;
    private char[][] board;

    public void initialize() {
        board = new char[3][3];
    }

    public void setPositionOfPlayerOnBoard(Player player, Position position) {

        board[position.getRow()][position.getColumn()] = player.getValue();
    }

    public char getPositionValueOnBoard(Position position) {
        return board[position.getRow()][position.getColumn()];
    }

    public boolean isBoardFull() {
        return getCountOfPositionsOccupied() == TOTAL_POSITIONS_ON_BOARD;
    }

    private int getCountOfPositionsOccupied() {

        return (int) Arrays.stream(board)
                .map(CharBuffer::wrap)
                .flatMapToInt(CharBuffer::chars)
                .filter(position -> position != EMPTY_POSITION_ON_BOARD)
                .count();
    }

    private boolean isFirstRowOccupiedBySamePlayer() {

        if (getPositionValueOnBoard(Position.ONE) != EMPTY_POSITION_ON_BOARD) {
            return (getPositionValueOnBoard(Position.ONE) == getPositionValueOnBoard(Position.TWO) &&
                    getPositionValueOnBoard(Position.TWO) == getPositionValueOnBoard(Position.THREE));
        }
        return false;
    }

    private boolean isSecondRowOccupiedBySamePlayer() {

        if (getPositionValueOnBoard(Position.FOUR) != EMPTY_POSITION_ON_BOARD) {
            return (getPositionValueOnBoard(Position.FOUR) == getPositionValueOnBoard(Position.FIVE) &&
                    getPositionValueOnBoard(Position.FIVE) == getPositionValueOnBoard(Position.SIX));
        }
        return false;
    }

    private boolean isThirdRowOccupiedBySamePlayer() {

        if (getPositionValueOnBoard(Position.SEVEN) != EMPTY_POSITION_ON_BOARD) {
            return (getPositionValueOnBoard(Position.SEVEN) == getPositionValueOnBoard(Position.EIGHT) &&
                    getPositionValueOnBoard(Position.EIGHT) == getPositionValueOnBoard(Position.NINE));
        }
        return false;
    }

    public boolean isAnyRowOccupiedBySamePlayer() {

        return isFirstRowOccupiedBySamePlayer() || isSecondRowOccupiedBySamePlayer() || isThirdRowOccupiedBySamePlayer();
    }

    private boolean isFirstColumnIsOccupiedBySamePlayer() {

        if (getPositionValueOnBoard(Position.ONE) != EMPTY_POSITION_ON_BOARD) {
            return (getPositionValueOnBoard(Position.ONE) == getPositionValueOnBoard(Position.FOUR) &&
                    getPositionValueOnBoard(Position.FOUR) == getPositionValueOnBoard(Position.SEVEN));
        }
        return false;
    }

    private boolean isSecondColumnOccupiedBySamePlayer() {

        if (getPositionValueOnBoard(Position.TWO) != EMPTY_POSITION_ON_BOARD) {
            return (getPositionValueOnBoard(Position.TWO) == getPositionValueOnBoard(Position.FIVE) &&
                    getPositionValueOnBoard(Position.FIVE) == getPositionValueOnBoard(Position.EIGHT));
        }
        return false;
    }

    private boolean isThirdColumnOccupiedBySamePlayer() {

        if (getPositionValueOnBoard(Position.THREE) != EMPTY_POSITION_ON_BOARD) {
            return (getPositionValueOnBoard(Position.THREE) == getPositionValueOnBoard(Position.SIX) &&
                    getPositionValueOnBoard(Position.SIX) == getPositionValueOnBoard(Position.NINE));
        }
        return false;
    }

    public boolean isAnyColumnOccupiedBySamePlayer() {
        return isFirstColumnIsOccupiedBySamePlayer() || isSecondColumnOccupiedBySamePlayer() || isThirdColumnOccupiedBySamePlayer();
    }

    private boolean isFirstDiagonalOccupiedBySamePlayer() {

        if (getPositionValueOnBoard(Position.ONE) != EMPTY_POSITION_ON_BOARD) {
            return (getPositionValueOnBoard(Position.ONE) == getPositionValueOnBoard(Position.FIVE) &&
                    getPositionValueOnBoard(Position.FIVE) == getPositionValueOnBoard(Position.NINE));
        }
        return false;
    }

    private boolean isSecondDiagonalOccupiedBySamePlayer() {

        if (getPositionValueOnBoard(Position.THREE) != EMPTY_POSITION_ON_BOARD) {
            return (getPositionValueOnBoard(Position.THREE) == getPositionValueOnBoard(Position.FIVE) &&
                    getPositionValueOnBoard(Position.FIVE) == getPositionValueOnBoard(Position.SEVEN));
        }
        return false;
    }

    public boolean isAnyDiagonalOccupiedBySamePlayer() {
        return isFirstDiagonalOccupiedBySamePlayer() || isSecondDiagonalOccupiedBySamePlayer();
    }
}