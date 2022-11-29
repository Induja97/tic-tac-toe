package com.game.tictactoe.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

public class GameResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Player currentPlayer;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Player nextPlayer;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String warning;

    public static GameResponseBuilder gameGameResponseBuilder() {
        return new GameResponseBuilder();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public static class GameResponseBuilder {

        private Player currentPlayer;
        private Player nextPlayer;
        private String status;
        private String result;
        private String warning;
        private GameResponse gameResponse;

        public GameResponseBuilder withCurrentPlayer(Player currentPlayer) {
            this.currentPlayer = currentPlayer;
            return this;
        }

        public GameResponseBuilder withNextPlayer(Player nextPlayer) {
            this.nextPlayer = nextPlayer;
            return this;
        }

        public GameResponseBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public GameResponseBuilder withResult(String result) {
            this.result = result;
            return this;
        }

        public GameResponseBuilder withWarning(String warning) {
            this.warning = warning;
            return this;
        }

        public GameResponse build() {
            this.gameResponse = new GameResponse();
            gameResponse.setCurrentPlayer(currentPlayer);
            gameResponse.setNextPlayer(nextPlayer);
            gameResponse.setResult(result);
            gameResponse.setStatus(status);
            gameResponse.setWarning(warning);
            return this.gameResponse;
        }

        public GameResponse getGameResponse() {
            return this.gameResponse;
        }
    }
}