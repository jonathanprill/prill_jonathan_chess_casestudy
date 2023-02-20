package com.prill.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.prill.chess.controller.PuzzleController;
import com.prill.chess.model.Puzzle;
import com.prill.chess.service.PuzzleService;

@WebMvcTest(PuzzleController.class)
public class PuzzleServiceTest {

	@MockBean
	PuzzleService puzzleService;

	@Autowired
	MockMvc mockMVC;

//	tests that the route to /puzzles is a valid route
	@Test
	public void testPuzzleRoute() throws Exception {
		mockMVC.perform(get("/puzzles")) // perform get
				.andExpect(status().isOk()); // expects http 200 - is successfuul
	}
	
//	tests get method for retrieving all puzzles
	@Test
	public void testPuzzleService() {
		List<Puzzle> puzzleList = puzzleService.getAllPuzzles();
		
		assertThat(puzzleService.getAllPuzzles().equals(puzzleList));
	}

}
