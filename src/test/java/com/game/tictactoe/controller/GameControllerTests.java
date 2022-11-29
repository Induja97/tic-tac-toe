package com.game.tictactoe.controller;

import com.game.tictactoe.domain.Player;
import com.game.tictactoe.service.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class GameControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameService gameService;

    @Test
    public void playGameHandler_APIFound() throws Exception {

        when(gameService.playGame(Player.X, 0, 0)).thenReturn("Successful Move");

        mvc.perform(post("/tic-tac-toe/play/{player}/{row}/{column}", Player.X, 0, 0))
                .andExpect(status().isOk());
    }
}