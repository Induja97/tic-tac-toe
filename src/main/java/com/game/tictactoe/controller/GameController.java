package com.game.tictactoe.controller;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(summary = "Play Tic Tac Toe Game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully played the Turn",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "409", description = "Invalid Position parameter",
                    content = @Content)})
    @PostMapping(path = "/tic-tac-toe/play/{player}/{position}")
    public ResponseEntity<String> playGameHandler(@PathVariable Player player,
                                                  @PathVariable int position) {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.playGame(player, position));
    }

    @Operation(summary = "Reset Tic Tac Toe Game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reset successful")})
    @PutMapping(path = "/tic-tac-toe/reset-game")
    public ResponseEntity<String> resetGameHandler() {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.resetGameState());
    }
}