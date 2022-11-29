package com.game.tictactoe.controller;

import com.game.tictactoe.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(path = "/tic-tac-toe/play/{player}")
    public ResponseEntity<String> playGameHandler(@PathVariable char player) {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.playGame(player));
    }
}