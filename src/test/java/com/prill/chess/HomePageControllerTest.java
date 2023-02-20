package com.prill.chess;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.prill.chess.controller.HomePageController;
import com.prill.chess.repository.CommentRepository;
import com.prill.chess.repository.PuzzleRepository;
import com.prill.chess.repository.TeamRepository;
import com.prill.chess.repository.UserRepository;

@WebMvcTest(HomePageController.class)
public class HomePageControllerTest {

	@MockBean
	CommentRepository commentRepository;
	@MockBean
	PuzzleRepository puzzleRepository;
	@MockBean
	TeamRepository teamRepository;
	@MockBean
	UserRepository userRepository;

	@Autowired
	MockMvc mockMVC;

	@Test
	public void testUserRoute() throws Exception {

		mockMVC.perform(get("/allteams")) // perform get
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("teamList")); // expects http 200 - is successfuul
		
			
	}
}